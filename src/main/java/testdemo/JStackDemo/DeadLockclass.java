package testdemo.JStackDemo;

/**
 * @author liuhai
 * @date 2019/8/9 9:20
 */
public class DeadLockclass implements Runnable {

    public boolean falg;// 控制线程

    DeadLockclass(boolean falg) {
        this.falg = falg;
    }


    @Override
    public void run() {

        if (falg) {
            while (true) {
                synchronized (Suo.o1) {
                    System.out.println("o1 " + Thread.currentThread().getName());
                    synchronized (Suo.o2) {
                        System.out.println("o2 " + Thread.currentThread().getName());
                    }
                }
            }
        }else {
            while (true) {
                synchronized (Suo.o2) {
                    System.out.println("o2 " + Thread.currentThread().getName());
                    synchronized (Suo.o1) {
                        System.out.println("o1 " + Thread.currentThread().getName());
                    }
                }
            }
        }
    }
    static class Suo {
        static Object o1 = new Object();
        static Object o2 = new Object();
    }
}
