package Semaphore;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author o_0sky
 * @date 2019/4/23 16:53
 */
public class TestRateLimiter implements Runnable {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final RateLimiter limiter = RateLimiter.create(2); // 允许每秒最多1个任务

    public static void main(String[] arg) {
        for (int i = 0; i < 10; i++) {
            limiter.acquire(); // 请求令牌,超过许可会被阻塞
            Thread t = new Thread(new TestRateLimiter());
            t.start();
        }
    }

    public void run() {
        System.out.println(sdf.format(new Date()) + " Task End..");
    }
}
