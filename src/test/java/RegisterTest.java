import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;
import com.example.demo.register.bean.SysUser;
import com.example.demo.register.bean.SysUserRole;
import com.example.demo.register.dao.SysUserDao;
import com.example.demo.register.dao.SysUserRoleDao;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RegisterTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterTest.class);


    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;


    @Test
    public void daoTest(){

        SysUser user =  new SysUser();
        user.setName("orakkyTest");
        user.setPassword("123456");
        int insert = sysUserDao.insert(user);
        System.out.println(insert);

    }

    @Test
    public void sysUserDaoTest(){

        List<SysUserRole> userRoles = sysUserRoleDao.listByUserId(10);


        System.out.println(userRoles.size());
        System.out.println(userRoles.get(0).getUserId());


    }
}
