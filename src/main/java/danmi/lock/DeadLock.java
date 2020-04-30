package danmi.lock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuhai
 * @date 2020/4/28 8:41
 */
public class DeadLock {

    private static ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("liuHai").build();
    private static ThreadPoolExecutor group = new ThreadPoolExecutor(20,
            1000, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), factory);
    static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {




    }

}

