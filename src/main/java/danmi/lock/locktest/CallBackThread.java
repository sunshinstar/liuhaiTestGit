package danmi.lock.locktest;

/**
 * @author liuhai
 * @date 2020/4/29 10:24
 */
public class CallBackThread implements Runnable {
    @Override
    public void run() {
        Thread.currentThread().setName("刘海");
        System.out.println(Thread.currentThread().getId()+"线程起来了");
    }
}
