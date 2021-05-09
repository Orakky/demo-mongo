import com.example.demo.DemoApplication;
import com.example.demo.annotation.CheckParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    



    
    /**
     *@Description
     *@Author Orakky
     *@Date 2021/5/9 11:31 下午
     *@Param []
     *@return void
     **/
    @Test
    public void checkTest(){

        check("aa");
        LOGGER.info("测试结束！");

    }


    @CheckParams("1111")
    public void check(String a){
        System.out.println("a:"+a);
    }
}
