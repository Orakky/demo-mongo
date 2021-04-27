import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;
import com.example.demo.mongo.bean.NewPerson;
import com.example.demo.mongo.bean.Person;
import com.example.demo.mongo.bean.PersonGroup;
import com.example.demo.mysql.dao.PersonDao;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 测试mysql数据源是否成功获取到数据
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class PersonDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDaoTest.class);

    @Autowired
    private PersonDao personDao;


    @Test
    public void personDaoTest(){
        List<Person> people = personDao.queryAllPerson();

        LOGGER.info(JSONObject.toJSONString(people));
    }



    @Test
    public void persontoPersonGroupTest(){

        List<Person> personList = personDao.queryAllPerson();
        List<PersonGroup> dataList = personToPersonGroup(personList);


    }

    /**
     * 分组组装数据
     * @return
     */
    private List<PersonGroup> personToPersonGroup(List<Person> personList) {

        //根据name分组
        List<PersonGroup> dataList = new ArrayList<>();

        
        Set<Integer> allAgeSet = new HashSet<>();
        personList.forEach(o->{
            o.setName(o.getName());
            allAgeSet.add(o.getAge());
        });

        //根据name分组
        personList.stream().collect(Collectors.groupingBy((o) -> StringUtils.joinWith("\t", o.getName()), Collectors.counting()))
                .forEach((v,k)->{
                    final PersonGroup group = new PersonGroup();
                    final String[] split = v.split("\t");
                    group.setName(split[0]);
                    group.setAge(Integer.parseInt(split[1]));
                    group.setTelTotal(Integer.parseInt(String.valueOf(k)));
                    dataList.add(group);
                });

        LOGGER.info("完成分组与组装 ： {}" + JSONObject.toJSONString(dataList));


        return dataList;
    }


    @Test
    public void personToNewPersonTest(){
        List<Person> personList = personDao.queryAllPerson();

        //组装新数据
        List<NewPerson> dataList = personToNewPerson(personList);

        //数据过滤，将age=10的数据过滤掉

        List<NewPerson> collect = dataList.stream().filter(o -> (o.getAge() != 10)).collect(Collectors.toList());
        LOGGER.info("将age=10的数据过滤掉：{}",JSONObject.toJSONString(collect));


        List<PersonGroup> list = new ArrayList<>();

         personList.stream().map(o -> {
            Person person = new Person();
            person.setName(o.getName());
            person.setAge(o.getAge());
            person.setTel(o.getTel());
            return person;
        }).filter(o -> (o.getAge() != 10)).collect(Collectors.groupingBy((o) -> StringUtils.joinWith("\t", o.getName(), o.getAge()), Collectors.counting()))
                .forEach((v,k)->{
                    final PersonGroup group = new PersonGroup();
                    final String[] split = v.split("\t");
                    group.setName(split[0]);
                    group.setAge(Integer.parseInt(split[1]));
                    group.setTelTotal(Integer.parseInt(String.valueOf(k)));
                    list.add(group);
                });

        LOGGER.info(JSONObject.toJSONString(list));
    }

    /**
     * 将person组装成newPerson
     * @param personList
     * @return
     */
    private List<NewPerson> personToNewPerson(List<Person> personList) {
        List<NewPerson> dataList = new ArrayList<>();

        personList.forEach((k)->{
            final NewPerson newPerson = new NewPerson();
            newPerson.setName(k.getName());
            newPerson.setAge(k.getAge());
            dataList.add(newPerson);
        });

        LOGGER.info("完成新集合的组装 : {}",JSONObject.toJSONString(dataList));
        return dataList;
    }






}
