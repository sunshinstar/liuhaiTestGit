package testdemo.thread;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.List;

/**
 * @author liuhai
 * @date 2019/9/19 16:02
 */
@EnableScheduling
public class Demo {

    @Test
    void test1() throws IOException {
        List dictList = null;
        System.out.println( toJson(dictList));

    }
    public  String toJson(Object obj)
    {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
