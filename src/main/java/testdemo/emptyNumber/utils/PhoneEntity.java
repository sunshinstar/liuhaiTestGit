package testdemo.emptyNumber.utils;

/**
 * @author liuhai
 * @date 2019/9/30 16:39
 * 空号检测实体
 */

public class PhoneEntity {

    /**
     * 手机号作为主键  因为手机号是唯一的 也只有一条记录
     */
    private String id;

    /**
     * 号码的监测状态
     */
    private String phoneStatus;

    /**
     * 当前号码监测的创建时间
     */
    private Long gmtCreate;

    /**
     * 当前号码监测状态的修改时间
     */
    private Long gmtModify;

    /**
     * 号码运营商
     */
    private String operatorType;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 来源渠道  接口或者通道
     */
    private String source;

    /**
     * 表名
     */
    private String tableName;


    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(String phoneStatus) {
        this.phoneStatus = phoneStatus;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Long gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
