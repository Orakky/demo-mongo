import com.example.demo.DemoApplication;
import com.example.demo.utils.Md5Utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MD5Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Test.class);


    @Test
    public void test(){
        String password = "123456";
        String salt = "1a2b3c4d";
        String s = MD5Util.fromPassToDBPass(password, salt);
        LOGGER.info("密码:{}",s);
    }
}
