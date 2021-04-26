import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;
import com.example.demo.mongo.bean.Person;
import com.example.demo.mysql.dao.PersonDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


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


}
