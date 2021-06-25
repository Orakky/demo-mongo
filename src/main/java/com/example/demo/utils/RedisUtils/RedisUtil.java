package com.example.demo.utils.RedisUtils;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 重新封装redisTemplate
 */
@Component
public class RedisUtil {



    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisUtil(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /**
     * 指定缓存过期时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key,long time){
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            LOGGER.info("指定缓存过期时间出现异常：{}",e.getMessage());
            return false;
        }
    }


    /**
     * 根据key获取缓存过期时间
     * @param key
     * @return 时间，返回0代表永久
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }


    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){

        try{
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            LOGGER.info("判断key是否存在出现异常：{}",e.getMessage());
            return false;
        }
    }


    /**
     * 删除缓存
     * @param key 可以传一个或者多个
     */
    public void del(String ... key){
        if(key!=null&&key.length > 0){
            if(key.length == 1){
                 redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

//    string 操作

    /**
     * 普通缓存获取
     * @param key
     * @return
     */
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存存放
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,Object value){
        try{
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            LOGGER.info("普通缓存存放异常:{}",e.getMessage());
            return false;
        }
    }


    /**
     * 普通缓存存放，并设置缓存时间
     * @param key
     * @param value
     * @param time 秒，time > 0 else no limit
     * @return
     */
    public boolean set(String key,Object value,long time){

        try{
            if(time > 0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }else{
                set(key,value);
            }
            return true;
        }catch (Exception e){
            LOGGER.info("普通缓存存放并设置缓存时间异常:{}",e.getMessage());
            return false;
        }
    }


    /**
     * 递增
     * @param key
     * @param delta > 0
     * @return
     */
    public long incr(String key,long delta){

        if(delta < 0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }


    /**
     * 递减
     * @param key
     * @param delta < 0
     * @return
     */
    public long decr(String key, long delta){

        if(delta < 0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,-delta);
    }

    //hash

    /**
     * hashget
     * @param key
     * @param item
     * @return
     */
    public Object hashGet(String key,String item){
        return redisTemplate.opsForHash().get(key,item);
    }


    /**
     * 获取hashkey对应所有的键值
     * @param key
     * @return
     */
    public Map<Object,Object> hashMapGet(String key){
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * 设置hashMap的值
     * @param key
     * @param map 对应多个键值对
     * @return
     */
    public boolean hashMapSet(String key,Map<String,Object> map){

        try{
            redisTemplate.opsForHash().putAll(key,map);
            return  true;
        }catch (Exception e){
            LOGGER.info("存放hashMap缓存时发生异常：{}",e.getMessage());
            return false;
        }
    }

    /**
     * 设置hashMap的值 并设置缓存时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean hashMapSet(String key,Map<String,Object> map,long time){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            LOGGER.info("存放hashMap缓存并设置缓存时间时发生异常:{}",e.getMessage());
            return false;
        }
    }


    /**
     * 向一张hash表存放数据，如果不存在将创建
     * @param key
     * @param item
     * @param value
     * @return
     */
    public boolean hashSet(String key,String item,Object value){

        try{
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        }catch (Exception e){
            LOGGER.info("往{}中存放数据时发生异常：{}",key,e.getMessage());
            return false;
        }
    }

    /**
     * 向一张hash表存放数据，如果不存在将创建并更新缓存时间
     * @param key
     * @param item
     * @param value
     * @param time 如果已有时间 则time会替换原来的缓存时间
     * @return
     */
    public boolean hashSet(String key,String item,Object value,long time){

        try{
            redisTemplate.opsForHash().put(key,item,value);
            if(time > 0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            LOGGER.info("往{}中存放数据并更新缓存时间时发生异常：{}",key,e.getMessage());
            return false;
        }
    }


    /**
     * 删除hash表中的值
     * @param key
     * @param item
     */
    public void hashDel(String key,Object... item){

        redisTemplate.opsForHash().delete(key,item);
    }


    /**
     * 判断hash表中是否存在该项的值
     * @param key
     * @param item
     * @return
     */
    public boolean hashHasKey(String key,String item){

        return redisTemplate.opsForHash().hasKey(key,item);
    }


    /**
     * hash递增，如果不存在，就会创建一个，并将新增后的值返回
     * @param key
     * @param item
     * @param by 要增加几（大于0）
     * @return
     */
    public double hashIncr(String key,String item,double by){

        return  redisTemplate.opsForHash().increment(key,item,by);
    }


    /**
     * hash递减
     * @param key
     * @param item
     * @param by
     * @return
     */
    public double hashDecr(String key,String item,double by){
        return redisTemplate.opsForHash().increment(key,item,-by);
    }

    //set


    /**
     * 根据key获取set中的所有值
     * @param key
     * @return
     */
    public Set<Object> setGet(String key){
        try{
            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            LOGGER.info("根据key获取set中的值发生异常：{}",e.getMessage());
            return Collections.EMPTY_SET;
        }
    }


    /**
     * 根据value从一个set中查询，是否存在
     * @param key
     * @param value
     * @return true 存在 false 不存在
     */
    public boolean setHasKey(String key,Object value){
        try{
            return redisTemplate.opsForSet().isMember(key,value);
        }catch (Exception e){
            LOGGER.info("查询set值发生异常:{}",e.getMessage());
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     * @param key
     * @param value
     * @return 成功塞入set缓存中的个数
     */
    public long sSet(String key,Object... value){
        try{
            return redisTemplate.opsForSet().add(key,value);
        }catch (Exception e){
            LOGGER.info("将数据放入set缓存中发生异常:{}",e.getMessage());
            return 0;
        }
    }


    /**
     * 将数据放入set缓存并设置时长
     * @param key
     * @param time
     * @param value
     * @return
     */
    public long sSet(String key, long time, Object... value){
        try{

            long count = sSet(key, value);
            if(time > 0){
                expire(key,time);
            }
            return count;
        }catch (Exception e){
            LOGGER.info("将将数据放入set缓存中并设置缓存时间发生异常:{}",e.getMessage());
            return 0;
        }
    }


    /**
     * 获取set缓存的长度
     * @param key
     * @return
     */
    public long sGetSetSize(String key){
        try{
            return redisTemplate.opsForSet().size(key);
        }catch (Exception e){
            LOGGER.info("获取set缓存长度发生异常:{}",e.getMessage());
            return 0;
        }
    }


    /**
     * 移除值为value
     * @param key
     * @param value
     * @return
     */
    public long setRemove(String key,Object... value){
        try{
           return redisTemplate.opsForSet().remove(key,value);
        }catch (Exception e){
            LOGGER.info("移除set中的值时发生异常:{}",e.getMessage());
            return 0;
        }
    }

    //list


    /**
     * 获取list缓存的内容
     * @param key
     * @param start
     * @param end 0到-1所有值
     * @return
     */
    public List<Object> listGet(String key,long start,long end){
        try{
            return redisTemplate.opsForList().range(key,start,end);
        }catch (Exception e){
            LOGGER.info("获取list中的缓存内容发生异常：{}",e.getMessage());
            return Collections.EMPTY_LIST;
        }
    }


    /**
     * 获取list缓存的长度
     * @param key
     * @return
     */
    public long listGetSize(String key){
        try{
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            LOGGER.info("获取list长度发生异常:{}",e.getMessage());
            return 0;
        }
    }


    /**
     * 通过索引获取list中的值
     * @param key
     * @param index
     * @return
     */
    public Object listGetIndex(String key,long index){
        try {
            return redisTemplate.opsForList().index(key,index);
        }catch (Exception e){
            LOGGER.info("获取list中的值时发生异常:{}",e.getMessage());
            return null;
        }
    }


    /**
     * 将list放入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean listSet(String key, Object value){
        try{
            redisTemplate.opsForList().rightPush(key,value);
            return true;
        }catch (Exception e){
            LOGGER.info("将list放入缓存时发生异常：{}",e.getMessage());
            return false;
        }
    }

    /**
     * 将list放入缓存，并设置缓存时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean listSet(String key, Object value,long time){
        try{
            redisTemplate.opsForList().rightPush(key,value);
            if(time > 0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            LOGGER.info("将list放入缓存并设置缓存时间时发生异常:{}",e.getMessage());
            return false;
        }
    }


    /**
     * 将list放入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean listSet(String key,List<Object> value){
        try{
            redisTemplate.opsForList().rightPushAll(key,value);
            return true;
        }catch (Exception e){
            LOGGER.info("将list放入缓存时发生异常：{}",e.getMessage());
            return false;
        }
    }


    /**
     * 将list放入缓存，并设置缓存时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean listSet(String key,List<Object> value,long time){
        try{
            redisTemplate.opsForList().rightPushAll(key,value);
            if(time > 0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            LOGGER.info("将list放入缓存并设置缓存时间时发生异常:{}",e.getMessage());
            return true;
        }
    }


    /**
     * 根据索引修改list中的数据
     * @param key
     * @param index
     * @param value
     * @return
     */
    public boolean listUpdateIndex(String key,long index,Object value){

        try{
            redisTemplate.opsForList().set(key,index,value);
            return true;
        }catch (Exception e){
            LOGGER.info("根据索引修改list中数据发生异常:{}",e.getMessage());
            return false;
        }
    }

    /**
     * 移除N个值为value的
     * @param key
     * @param count
     * @param value
     * @return
     */
    public long listRemove(String key,long count,Object value){
        try{
            return redisTemplate.opsForList().remove(key,count,value);
        }catch (Exception e){
            LOGGER.info("移除list中{}个值为{}的数据时发生异常:{}",count,value,e.getMessage());
            return 0;
        }
    }



}

