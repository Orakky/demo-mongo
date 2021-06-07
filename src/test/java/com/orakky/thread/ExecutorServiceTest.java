package com.orakky.thread;


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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 线程池测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ExecutorServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorServiceTest.class);


    @Autowired
    private PersonDao personDao;



    @Test
    public void threadTest(){

        //获取对应得person列表
        List<Person> personList = personDao.queryAllPerson();

        ExecutorService mExecutorService = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(personList.size());
        for (int i = 0; i < personList.size(); i++) {
            int finalI = i;
            mExecutorService.submit(()->{
                personList.get(finalI).setName("pppp" + finalI + personList.get(finalI).getName());
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LOGGER.error("多线程发生异常",e);
        }



        for (int i =0; i < personList.size(); i++){
            System.out.println(i + "----"+personList.get(i).getName());
        }


        for (int i = 0; i < personList.size(); i++) {
            System.out.println(i + personList.get(i).getPreffixName());
        }
    }

}
