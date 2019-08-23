package testdemo.socket.second;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuhai
 * @date 2019/8/14 16:51
 */
public class HandlerExecutorPool {
    private ExecutorService executorService;

    public HandlerExecutorPool(int maxSize, int queueSize) {
        this.executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxSize, 12L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));

    }

    public void excute(Runnable task) {
        executorService.execute(task);

    }
}
