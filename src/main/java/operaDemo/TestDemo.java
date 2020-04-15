package operaDemo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuhai
 * @date 2020/1/6 11:11
 */
public class TestDemo {


    ExecutorService interfaceQuery = Executors.newFixedThreadPool(10);
    @Test
    void test() throws InterruptedException {
        for (int k = 0; k <100 ; k++) {
            List<String> pendingList = new ArrayList<>();
            List<String> count = new CopyOnWriteArrayList<>();

            CountDownLatch latch = new CountDownLatch(2000);
            for (int i = 0; i <100000 ; i++) {
                pendingList.add("11111111111");
                if (pendingList.size() >= 50) {

                    interfaceQuery.submit(() -> {
                        try {
                            count.add("111");
                        } catch (Exception e) {

                        } finally {
                            latch.countDown();
                        }
                    });
                    pendingList = new ArrayList<>();
                }
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(count.size());
        }



    }

    @Test
    void test2() throws InterruptedException {
        Integer number = 0;
        intadd(number);
        System.out.println(number);
    }

    void intadd( Integer i ){
        for (int j = 0; j <100 ; j++) {
            i++;
        }
    }
}
