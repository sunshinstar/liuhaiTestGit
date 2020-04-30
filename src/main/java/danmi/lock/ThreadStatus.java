package danmi.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author liuhai
 * @date 2020/4/28 14:16
 */
public class ThreadStatus {

    static int count = 0;


    public static void main(String[] args) throws Exception {
        new Thread(new Running(), "RunningThread").start();
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread-1").start();
        new Thread(new Waiting(), "WaitingThread-2").start();
        // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }


    static class Running implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                i++;
            }
        }
    }

    static class TimeWaiting implements Runnable {
        @Override
        public void run() {

            while (true) {
                ThreadStatus.SleepUtils.second(100);
            }
        }
    }

    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (ThreadStatus.Waiting.class) {
                    try {
                        ThreadStatus.Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
        }
    }


    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized(ThreadStatus.Blocked.class){
                while (true) {
                    ThreadStatus.SleepUtils.second(100);
                }
            }
        }
    }

    static class SleepUtils {
        public static final void second(long seconds) {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
            }
        }
    }
}
