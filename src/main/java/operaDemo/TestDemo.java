package operaDemo;

import org.junit.jupiter.api.Test;

import java.net.URLDecoder;

/**
 * @author liuhai
 * @date 2020/1/6 11:11
 */
public class TestDemo {


    @Test
    void test(){
        String data ="channelScheduleData:2020-01-06:359:error";
        System.out.println(data.substring(0,data.lastIndexOf(":")));
    }

    @Test
    void test2(){
        String longUrl = "https%3A%2F%2Fwww.so.com%2Fs%3Fie%3Dutf-8%26src%3D360chrome_toolbar_search%26q%3D%25E6%2598%25AF%25E7%259A%2584";
        System.out.println(URLDecoder.decode(longUrl));
        System.out.println(URLDecoder.decode(URLDecoder.decode(longUrl)) );
    }
}
