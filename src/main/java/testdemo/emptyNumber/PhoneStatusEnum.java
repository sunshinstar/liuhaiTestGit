package testdemo.emptyNumber;

/**
 * 电话号码的状态枚举类
 */
public enum PhoneStatusEnum {
    //电话号码状态枚举类
    EMPTY_PHONE("0", "空号"),
    USER_PHONE("1", "实号"),
    DOWNTIME_PHONE("2", "停机"),
    NOEXIST_PHONE("3", "库无"),
    SILENCE_PHONE("4", "沉默号"),
    RISK_PHONE("5", "风险号"),
    ;

    /**
     * value
     */
    private final String code;

    /**
     * message
     */
    private final String message;

    /**
     * 私有构造方法
     *
     * @param code
     * @param message
     */
    private PhoneStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public static String getByValue(String value) {
        for (PhoneStatusEnum typeEnum : PhoneStatusEnum.values()) {
            if (typeEnum.code.equals(value)) {
                return typeEnum.getMessage();
            }
        }
        throw new IllegalArgumentException("No element matches " + value);
    }
}
