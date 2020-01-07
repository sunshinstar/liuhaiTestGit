package testdemo.emptyNumber.utils;

import java.io.Serializable;

/**
 * 接口请求的数据请求体包装类
 *
 * @author liuhai
 * @date 2019/12/3 16:34
 */
public class DetectionRequestEntity  implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 批次ID
     */
    private String id;

    /**
     * 当前时间戳
     */
    private String time;

    /**
     * 传过来的秘钥
     */
    private String key;

    /**
     * 传过来的电话号码字符串 其实是map的集合字符串
     */
    private String mobiles;

    /**
     * 回调地址
     */
    private String receiptAddress;

    /**
     * 常量字段  是一个json的字符串  要原封不动的再通过接口带回去
     */
    private String constantField;


    public String getConstantField() {
        return constantField;
    }

    public void setConstantField(String constantField) {
        this.constantField = constantField;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    @Override
    public String toString() {
        return "DetectionRequestEntity{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", key='" + key + '\'' +
                ", mobiles='" + mobiles + '\'' +
                ", receiptAddress='" + receiptAddress + '\'' +
                ", constantField='" + constantField + '\'' +
                '}';
    }
}
