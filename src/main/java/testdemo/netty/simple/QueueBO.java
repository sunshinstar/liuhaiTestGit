package testdemo.netty.simple;


import lombok.Data;

import java.util.List;

/**
 * @author hjt001
 * <p>
 * 队列属性类
 */
@Data
public class QueueBO {

    /**
     * 队列锁
     */
    private IQueueLock readLock;
    private IQueueLock cntLock;

    private List<String> channelList;

    private String queueName;



    /**
     * 最近活跃时间（入队/出队时间）
     */
    private Long lastActiveTime;


    /**
     * 总入队量
     */
    private Long totalPutNumber;


    /**
     * 总出队量
     */
    private Long totalOutNumber;

    /**
     * 队列深度
     */
    private Long queueDepth;



    public QueueBO() {
        this.lastActiveTime = 0L;
        this.totalOutNumber = 0L;
        this.totalPutNumber = 0L;
        this.queueDepth = 0L;
        readLock = new MessageReentrantLock();
        cntLock = new MessageReentrantLock();
    }



    public IQueueLock getReadLock() {
        return readLock;
    }

    public void setReadLock(IQueueLock readLock) {
        this.readLock = readLock;
    }

    public IQueueLock getCntLock() {
        return cntLock;
    }

    public void setCntLock(IQueueLock cntLock) {
        this.cntLock = cntLock;
    }

    public List<String> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<String> channelList) {
        this.channelList = channelList;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Long getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Long lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public Long getTotalPutNumber() {
        return totalPutNumber;
    }

    public void setTotalPutNumber(Long totalPutNumber) {
        this.totalPutNumber = totalPutNumber;
    }

    public Long getTotalOutNumber() {
        return totalOutNumber;
    }

    public void setTotalOutNumber(Long totalOutNumber) {
        this.totalOutNumber = totalOutNumber;
    }

    public Long getQueueDepth() {
        return queueDepth;
    }

    public void setQueueDepth(Long queueDepth) {
        this.queueDepth = queueDepth;
    }

}
