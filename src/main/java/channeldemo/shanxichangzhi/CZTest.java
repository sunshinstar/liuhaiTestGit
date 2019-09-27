package channeldemo.shanxichangzhi;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;

import java.nio.charset.Charset;

public class CZTest {

    private final static String URL = "http://120.227.5.17:9001";
    private final static String appKey = "c410f075";
    private final static String appSecret = "2a43e445f64d8c5b";

    public static void main(String[] args) throws Exception {
        send();
    }

    private static void send() throws Exception {
        final String add = "/sendVsms";
        Long timeMillis = System.currentTimeMillis();
        String modeId = "202424";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appKey", appKey);
        jsonObject.put("ts", timeMillis.toString());
        jsonObject.put("modeId", modeId);
        jsonObject.put("sign", SecureUtil.md5(modeId + timeMillis + appKey + appSecret));
        jsonObject.put("mobiles", new String[]{"13433835760"});
        jsonObject.put("notifyUrl", "http://119.23.65.189/sms-tool/voice/czyd");
//        jsonObject.put("sendTime", ""); //定时发送使用


        String result = Request.Post(URL + add).body(new StringEntity(jsonObject.toString())).connectTimeout(5000).socketTimeout(60000)
                .execute().returnContent().asString(Charset.forName("utf-8"));
        System.out.println(result);
    }

}
