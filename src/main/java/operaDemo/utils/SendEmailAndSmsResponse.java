package operaDemo.utils;

/**
 * @author liuhai
 * @date 2019/11/11 11:26
 */
public class SendEmailAndSmsResponse {

    String taskId;
    String sequenceId;
    String partnerId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "SendEmailAndSmsResponse{" +
                "taskId='" + taskId + '\'' +
                ", sequenceId='" + sequenceId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                '}';
    }
}
