import com.example.demo.DemoApplication;
import com.example.demo.annotation.CheckParams;
import com.example.demo.aspect.Check;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName CheckParamsTest
 * @DESCRIPTION checkParams 自定义注解的测试类
 * @Author Orakky
 * @Date 2021/5/9 11:21 下午
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CheckParamsTest {
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckParamsTest.class);
    

    @Autowired
    private Check check;


    
    /**
     *@Description
     *@Author Orakky
     *@Date 2021/5/9 11:31 下午
     *@Param []
     *@return void
     **/
    @Test
    public void checkTest(){

        String a = "2";
        check.check(a);
        LOGGER.info("测试结束！");

    }



}
