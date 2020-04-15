package miaodiDemo.HttpTest;

import miaodiDemo.HttpTest.common.Config;
import miaodiDemo.HttpTest.common.HttpUtil;

/**
 * 短信API发送
 *
 * @author JiangPengFei
 * @version $Id: javaHttpNewApiDemo, v 0.1 2019/1/23 11:42 JiangPengFei Exp $$
 */
public class SmsApiHttpSendTest {

    /**
     * 号码检测接口
     * 接口文档地址：http://www.miaodiyun.com/doc/https_sms.html
     */
    public void execute() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("accountSid").append("=").append(Config.ACCOUNT_SID);
        sb.append("&developerId").append("=").append("100023");
        sb.append("&accountId").append("=").append("109385");
        sb.append("&monitorMobiles").append("=").append("13430596715,sa,13430596715 ");
        String body = sb.toString() + HttpUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);
        String result = HttpUtil.post(Config.BASE_URL, body);
        System.out.println(result);

    }

    public static void main(String[] args) {
        SmsApiHttpSendTest am = new SmsApiHttpSendTest();
        try {
            am.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
