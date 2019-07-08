package ThreadLocal.NumUtil;

import cn.hutool.core.date.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuhai
 * @date 2019/6/17 8:57
 */
public class NumUtil {
    private static ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(
            ()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static Date parse(String dateStr) {
        Date date = null;
        try {
            date = threadLocal.get().parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 20; i++) {
            service.execute(()->{
                System.out.println(DateUtil.parse("2019-06-01 16:34:30"));
            });
        }
        service.shutdown();
    }
}
