import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;
import com.example.demo.mongo.bean.Person;
import com.example.demo.mongo.service.PersonService;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
