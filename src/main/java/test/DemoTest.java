package test;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author o_0sky
 * @date 2019/4/30 11:12
 */
public class DemoTest {


    @Test
    public void test1() {
        StringBuffer sb = new StringBuffer("你好");
        List list = new ArrayList();
        list.add(sb);
        sb = new StringBuffer("啊");
        list.add(sb);
        System.out.println(JSON.toJSONString(list));
        System.out.println(list.size());
        list.clear();
        System.out.println(list.size());
    }

    @Test
    @Scheduled(fixedRate = 1000)
    public void test2() {
        while (true) {
            try {
                Thread.sleep(5000);
                System.err.println("111111111111111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test3() throws ParseException {
        String s = "0";
        System.out.println(Integer.valueOf(s));
    }


}
