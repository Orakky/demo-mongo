import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;
import com.example.demo.mongo.bean.NewPerson;
import com.example.demo.mongo.bean.Person;
import com.example.demo.mongo.service.PersonService;
import com.example.demo.mysql.dao.PersonDao;
import com.example.demo.utils.RedisUtils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.List;

/**
 * 测试jedis
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class JedisTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(JedisTest.class);


    @Autowired
    private PersonDao personDao;

    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testJedis(){


        //1 连接redis

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //2 操作redis

        jedis.set("name","orakky");

        String name = jedis.get("name");
        System.out.println(name);
        //3 关闭连接

        jedis.close();
    }


    @Test
    public void testRedis(){

        redisTemplate.opsForValue().set("testKey","redisTest");

        Object testKey = redisTemplate.opsForValue().get("testKey");

        LOGGER.info(String.valueOf(testKey));


    }

    @Test
    public void deleteRedis(){
        Boolean testKey = redisTemplate.delete("testKey");

        LOGGER.info(String.valueOf(testKey));

    }




    @Test
    public void addPersonRedis(){
        NewPerson person = new NewPerson();

        person.setName("Orakky");
        person.setAge(21);

        redisTemplate.opsForValue().set("testPerson",person);

        Object testKey = redisTemplate.opsForValue().get("testPerson");

        LOGGER.info(JSONObject.toJSONString(testKey));

    }


    @Test
    public void testHasKey(){

        List<Person> personList = personDao.queryAllPerson();

         redisUtil.listSet("personList", personList);


    }



}
