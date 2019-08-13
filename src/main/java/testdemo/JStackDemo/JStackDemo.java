package testdemo.JStackDemo;

/**
 * @author liuhai
 * @date 2019/8/9 9:19
 */
public class JStackDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new DeadLockclass(true));
        Thread t2 = new Thread(new DeadLockclass(false));
        t1.start();//启动一个线程
        t2.start();//启动另一个线程
    }

}
