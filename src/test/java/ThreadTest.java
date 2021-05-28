import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@EnableAsync
public class ThreadTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest.class);


    @Test
    public void threadPoolTest(){
        List<Integer> dataList = new ArrayList<>();

      for(int i =0; i< 1000;i++){
          dataList.add(i);
      }

      LOGGER.info("原始数据:{}", JSONObject.toJSONString(dataList));

        //多线程处理
//      dataProcess(dataList);

        //单线程
        long t1 = System.currentTimeMillis();

        for(int i = 0; i < dataList.size(); i++){
            addNum(dataList.get(i));
        }
        LOGGER.info("单线程耗时:{}ms",System.currentTimeMillis() - t1);
        LOGGER.info("单线程处理后的数据:{}",JSONObject.toJSONString(dataList));

        //2 多线程-1
        threadPoolProcess(dataList);
    }


    /**
     * 线程处理
     * @param dataList
     * @return
     */
    private void dataProcess(List<Integer> dataList) {

        //动态创建线程池大小
//
//        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2,dataList.size(),
//                200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(2));



        //多线程跑任务
        long start = System.currentTimeMillis();
        List<Integer> jobList = new ArrayList<>();

        //执行批量任务
      for(int i =0 ; i < dataList.size();i++){
          jobList.add(addNum(dataList.get(i)));
      }

        LOGGER.info("多线程结束:{}ms",System.currentTimeMillis() - start);



    }


//    @Async
    Integer addNum(Integer num){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return num + 1;
    }

    /**
     * 线程池
     * @param dataList
     */
    void threadPoolProcess(List<Integer> dataList){
        ExecutorService mExecutor = Executors.newFixedThreadPool(10);

        long t1 = System.currentTimeMillis();
        for(int i =0; i < dataList.size(); i++){
            int finalI = i;
            mExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                    addNum(dataList.get(finalI));
                }
            });
        }

        LOGGER.info("newFixedThreadPool耗时:{}ms",System.currentTimeMillis() - t1);
        LOGGER.info("newFixedThreadPool处理后的数据:{}", JSONObject.toJSONString(dataList));

    }


}
