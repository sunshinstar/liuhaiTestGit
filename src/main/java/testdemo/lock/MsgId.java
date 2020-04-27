/**
 *
 */
package testdemo.lock;

import com.zx.sms.common.util.CachedMillisecondClock;

import java.io.Serializable;

/**
 *
 * @author huzorro(huzorro @ gmail.com)
 *
 */
public class MsgId implements Serializable {
    private static final long serialVersionUID = 945466149547731811L;
    private int month;
    private int day;
    private int hour;
    private int minutes;
    private int seconds;
    private int gateId;
    private int sequenceId;
    private long originalSequenceId;

    public MsgId(Boolean needNewSequence) {
        if (needNewSequence) {
            long timeMillis = CachedMillisecondClock.INS.now();
            setMonth(Integer.parseInt(String.format("%tm", timeMillis)));
            setDay(Integer.parseInt(String.format("%td", timeMillis)));
            setHour(Integer.parseInt(String.format("%tH", timeMillis)));
            setMinutes(Integer.parseInt(String.format("%tM", timeMillis)));
            setSeconds(Integer.parseInt(String.format("%tS", timeMillis)));
            setGateId(Integer.parseInt("1010"));
            setSequenceId((int) DefaultSequenceNumberUtil.getMsgIdSequenceNo());
        }
    }

    public static void main(String[] args) {
        MsgId msgId = new MsgId();
        System.out.println(msgId);
    }

    public MsgId() {
        this(CachedMillisecondClock.INS.now(),
                Integer.parseInt("1010"),
                (int) DefaultSequenceNumberUtil.getMsgIdSequenceNo());
    }

    /**
     *
     * @param timeMillis
     */
    public MsgId(long timeMillis) {

        this(timeMillis,
                Integer.parseInt("1010"),
                (int) DefaultSequenceNumberUtil.getMsgIdSequenceNo());
    }

    /**
     *
     * @param msgIds
     */
    public MsgId(String msgIds) {
        setMonth(Integer.parseInt(msgIds.substring(0, 2)));
        setDay(Integer.parseInt(msgIds.substring(2, 4)));
        setHour(Integer.parseInt(msgIds.substring(4, 6)));
        setMinutes(Integer.parseInt(msgIds.substring(6, 8)));
        setSeconds(Integer.parseInt(msgIds.substring(8, 10)));
        setGateId(Integer.parseInt(msgIds.substring(10, 17)));
        setSequenceId(Integer.parseInt(msgIds.substring(17, 22)));
    }

    /**
     *
     * @param timeMillis
     * @param gateId
     * @param sequenceId
     */
    public MsgId(long timeMillis, int gateId, int sequenceId) {
        setMonth(Integer.parseInt(String.format("%tm", timeMillis)));
        System.out.println(getMonth());
        setDay(Integer.parseInt(String.format("%td", timeMillis)));
        System.out.println(getDay());
        setHour(Integer.parseInt(String.format("%tH", timeMillis)));
        System.out.println(getHour());
        setMinutes(Integer.parseInt(String.format("%tM", timeMillis)));
        System.out.println(getMinutes());
        setSeconds(Integer.parseInt(String.format("%tS", timeMillis)));
        System.out.println(getSeconds());
        setGateId(gateId);
        setSequenceId(sequenceId);
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * @return the minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * @param minutes the minutes to set
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * @return the seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * @param seconds the seconds to set
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * @return the gateId
     */
    public int getGateId() {
        return gateId;
    }

    /**
     * @param gateId the gateId to set
     */
    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    /**
     * @return the sequenceId
     */
    public int getSequenceId() {
        return sequenceId & 0xffff;
    }

    public int getSequenceId111() {
        return sequenceId;
    }

    public long getOriginalSequenceId() {
        return originalSequenceId;
    }

    public void setOriginalSequenceId(long originalSequenceId) {
        this.originalSequenceId = originalSequenceId;
    }

    /**
     * @param sequenceId the sequenceId to set
     */
    public void setSequenceId(int sequenceId) {
        this.originalSequenceId = sequenceId;
        this.sequenceId = sequenceId & 0xffff;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String
                .format("%1$02d%2$02d%3$02d%4$02d%5$02d%6$07d%7$05d",
                        month, day, hour, minutes, seconds, gateId, sequenceId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + day;
        result = prime * result + gateId;
        result = prime * result + hour;
        result = prime * result + minutes;
        result = prime * result + month;
        result = prime * result + seconds;
        result = prime * result + sequenceId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MsgId other = (MsgId) obj;
        if (day != other.day)
            return false;
        if (gateId != other.gateId)
            return false;
        if (hour != other.hour)
            return false;
        if (minutes != other.minutes)
            return false;
        if (month != other.month)
            return false;
        if (seconds != other.seconds)
            return false;
        if (sequenceId != other.sequenceId)
            return false;
        return true;
    }


}
