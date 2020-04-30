package danmi.lock;

/**
 * @author liuhai
 * @date 2020/4/28 15:37
 */
public class Example {

    public static void main(String[] args){
        Counter counterB = new Counter();
        Counter counterA = new Counter();
        Thread  threadA = new CounterThread(counterA);
        Thread  threadB = new CounterThread(counterB);
        threadA.start();
        threadB.start();
    }

}
