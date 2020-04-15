package testdemo.netty.simple;

import java.util.concurrent.locks.ReentrantLock;

public class MessageReentrantLock implements IQueueLock {

    private static final ReentrantLock LOCK = new ReentrantLock();

    @Override
    public void lock() {
        LOCK.lock();
    }

    @Override
    public void unlock() {
        LOCK.unlock();
    }
}
