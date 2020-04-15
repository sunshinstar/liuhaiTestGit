package testdemo.netty.simple;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuhai
 * @date 2020/3/31 9:52
 */
public class Demo {


    private final static Integer corePoolSize = 3;

    private final static String FREQUENCY_PHONE_QUEUE_NAME = "saveFrequencyPhone";

    /**
     * 线程池
     */
    ExecutorService saveFrequencyService = new ThreadPoolExecutor(corePoolSize,
            corePoolSize + 1,
            5 * 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new DefaultThreadFactory("SaveFrequency-"));

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.put("key1", 1212);
        jsonObject.put("key2", 2222);
        System.out.println(jsonObject1.toJSONString());
        //BeanUtil.copyProperties(jsonObject,jsonObject1);
        BeanUtil.copyProperties(jsonObject,jsonObject1);
//        jsonObject.remove("key1");
        System.out.println(jsonObject1.toJSONString());

    }
}
