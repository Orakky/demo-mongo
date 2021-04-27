import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;
import com.example.demo.mongo.bean.BaseCondition;
import com.example.demo.mongo.bean.Person;
import com.example.demo.mongo.service.PersonService;
import org.bson.Document;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class PersonServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceTest.class);

    @Autowired
    private PersonService personService;


    @Test
    public void personTest(){
        List<Person> allPerson = personService.findAllPerson();
        LOGGER.info(JSONObject.toJSONString(allPerson));
    }


    /**
     * 测试分页查询
     */
    @Test
    public void personPageTest(){

        BaseCondition condition = new BaseCondition();

        condition.setNameStr("Orakky");
        condition.setNumber(0);
        condition.setSize(5);
        List<Document> documents = personService.queryPeron(condition);

        LOGGER.info("分页查询，当前页有: {}",JSONObject.toJSONString(documents));
    }
}
