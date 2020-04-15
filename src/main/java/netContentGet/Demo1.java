package netContentGet;

import org.apache.commons.lang3.RandomUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liuhai
 * @date 2020/3/18 10:13
 */
public class Demo1 {

    public static void main(String[] args) {
        Map map = new ConcurrentHashMap();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName()+"==="+ getNextAtomicValue(sequenceId, Limited));
                }
            }
        });
        t.start();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName()+"==="+ getNextAtomicValue(sequenceId, Limited));
                }
            }
        });
        t1.start();
    }


    public static long getNextAtomicValue(AtomicLong atomicObj, long limited) {
        long ret = atomicObj.getAndIncrement();

        if (ret > limited) { // Long.MAX_VALUE - 0xfff
            //双重判断，只能有一个线程更新值
            if (atomicObj.get() > limited) {
                atomicObj.set(0);
                return 0;
            } else {
                return atomicObj.getAndIncrement();
            }
        } else {
            return ret;
        }
    }

    private final static long Limited = 0x7fffffffffff0000L;
    private final static AtomicLong sequenceId = new AtomicLong(Math.abs(RandomUtils.nextInt()));


}
