import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;
import com.example.demo.mongo.bean.Person;
import com.example.demo.mysql.dao.PersonDao;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ThreadPoolTest {



    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolTest.class);


    @Autowired
    private PersonDao personDao;


    @Test
    public void mainThread() throws InterruptedException, ExecutionException {
        List<String> list = new ArrayList<>();


        //获取所有的person集合
        List<Person> personList = personDao.queryAllPerson();

        for(int i = 0 ; i < personList.size(); i++){
            System.out.println(personList.get(i).getName());
        }


        int threadSize = 3;//每500条数据开启一个线程
        int remainder =personList.size()%threadSize;
        int threadNum  = 0;//线程数
        if(remainder == 0){
            threadNum  = personList.size()/threadSize;
        } else {
            threadNum  = personList.size()/threadSize + 1;
        }

        ExecutorService eService = Executors.newFixedThreadPool(threadNum );//创建一个线程池

        List<Callable<String>> cList = new ArrayList<>();
        Callable<String> task = null;
        List<Person> sList = null;

        for(int i=0;i<threadNum;i++){
            if(i == threadNum - 1){
                sList = personList.subList(i*threadSize, personList.size());
            } else {
                sList = personList.subList(i*threadSize, (i+1)*threadSize);
            }
            final List<Person> nowList = sList;
            task = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    StringBuffer sb = new StringBuffer();
                    for(int j=0;j<nowList.size();j++){
                        sb.append("xxx"+nowList.get(j).getName());
                        System.out.println(sb);
                    }
                    return sb.toString();
                }
            };
            cList.add(task);
        }
        List<Future<String>> results = eService.invokeAll(cList);
        for(Future<String> str:results){
            System.out.println(str.get());
        }
        eService.shutdown();




        for(int i = 0 ; i < personList.size(); i++){
            System.out.println("-----"+personList.get(i).getName());
        }
    }




    @Test
    public void paralleStreamTest() throws InterruptedException {
        List<Person> personList = personDao.queryAllPerson();

        long t1 = System.currentTimeMillis();

        personList.parallelStream().forEach(k->{

            try {
                Thread.sleep(10);
                k.setName(k.getName() + "ppppp");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });


        for (int i = 0; i < personList.size(); i++) {
            System.out.println(personList.get(i).getName());
        }

        LOGGER.info("paralle耗时:{}ms",System.currentTimeMillis() - t1);
    }

    @Test
    public void normalTest(){
        List<Person> personList = personDao.queryAllPerson();

        long t1 = System.currentTimeMillis();

        personList.stream().forEach(k->{
            try {
                Thread.sleep(10);
                k.setName(k.getName() + "pppp");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });


        for (int i = 0; i < personList.size(); i++) {
            System.out.println(personList.get(i).getName());
        }

        LOGGER.info("nomarl耗时:{}ms",System.currentTimeMillis() - t1);
    }


    @Test
    public void executorTest(){
        List<Person> personList = personDao.queryAllPerson();

        long t1 = System.currentTimeMillis();

        ExecutorService mExecutorService = Executors.newFixedThreadPool(5);

        for(int i=0;i < personList.size();i++){
            int finalI = i;
            mExecutorService.submit((Callable<Object>) () -> {
                try {
                    Thread.sleep(10);
                    personList.get(finalI).setName(personList.get(finalI).getName() + "pppp");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return personList.get(finalI);
            });
        }

        for (int i = 0; i < personList.size(); i++) {
            System.out.println(personList.get(i).getName());
        }

        LOGGER.info("executor耗时:{}ms",System.currentTimeMillis() - t1);




    }


    @Test
    public void callTest() throws ExecutionException, InterruptedException {
        List<Person> personList = personDao.queryAllPerson();

        int taskSize = 5;
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);

        //创建多个有返回值的任务
        List<Future> futureList = new ArrayList<>();

        //将集合进行分批
        List<List<Person>> dataList = ListUtils.partition(personList,5);

        for(int i =0; i < taskSize; i++){
            for(int j =0; j < dataList.size(); j++){
                Callable c = new MyCallable(j+" ",dataList.get(j));
                //执行任务并获取Future对象
                Future f = pool.submit(c);
                futureList.add(f);
            }

        }

        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future f : futureList) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            LOGGER.info(JSONObject.toJSONString(f.get()));
        }
    }


    private class MyCallable implements Callable<List<Person>> {

        private String taskNum;

        private List<Person> personList;

        public MyCallable(String taskNum, List<Person> personList) {
            this.taskNum = taskNum;
            this.personList = personList;
        }

        public List<Person> call() throws Exception{
            long t1 = System.currentTimeMillis();

            personList.stream().forEach(k->{
                try {
                    Thread.sleep(10);
                    k.setName(k.getName() + "pppp");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
            return personList;
        }
    }


    @Test
    public void subList(){
        List<Person> personList = personDao.queryAllPerson();

        System.out.println(personList.size());
        List<List<Person>> partition = ListUtils.partition(personList, 3);

        for(int i = 0; i < partition.size(); i++){
            LOGGER.info(JSONObject.toJSONString(partition.get(i)));
        }


    }
}