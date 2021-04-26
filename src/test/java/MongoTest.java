import com.example.demo.DemoApplication;
import com.example.demo.mongo.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 测试mongo是否成功
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MongoTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(MongoTest.class);


    @Autowired
    private PersonService personService;


    @Test
    public void testMongo(){
        long t1 = System.currentTimeMillis();
        personService.savePersonToMongo();

        long t2 = System.currentTimeMillis();

        LOGGER.info("将数据存入mongodb，一共花了{}ms",t2-t1);

    }


    @Test
    public void testGroupMongo(){
        personService.savePersonGroupToMongo();
    }


}
