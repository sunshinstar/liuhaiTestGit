package Semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * @author o_0sky
 * @date 2019/4/23 16:55
 */
public class TestSemaphore implements Runnable {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final Semaphore semaphore = new Semaphore(5, true); // 允许并发的任务量限制为5个

    public static void main(String[] arg) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new TestSemaphore());
            t.start();
        }
    }

    public void run() {
        try {
            semaphore.acquire(); // 获取信号量,不足会阻塞
            System.out.println(sdf.format(new Date()) + " Task Start..");
            Thread.sleep(5000);
            System.out.println(sdf.format(new Date()) + " Task End..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // 释放信号量
        }
    }

}
