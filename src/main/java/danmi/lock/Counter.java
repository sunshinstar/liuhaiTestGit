package danmi.lock;

/**
 * @author liuhai
 * @date 2020/4/28 15:37
 */
public class Counter {
    static long count = 0;

    public static synchronized void add(long value){
        count += value;
        System. out.println(Thread. currentThread().getName()+":"+ count);
    }

}
