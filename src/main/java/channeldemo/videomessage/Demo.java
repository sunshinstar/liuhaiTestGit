package channeldemo.videomessage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuhai
 * @date 2019/5/24 14:04
 */
public class Demo {



    public static void main(String[] args) throws IOException {

         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /**
         * 设置请求和传输超时时间
         */
         RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();


        CloseableHttpClient httpClient = HttpClients.createDefault();
        //String url ="http://miaodi.f3322.net:7089/gateway/sendVideoStatus/videoMessageReveiveData";
        //公网地址  直接映射访问11.1服务器7089端口
        //String url ="http://119.23.65.189:8088/gateway/sendVideoStatus/videoMessageReveiveData";
        // String url = "http://127.0.0.1:8089/gateway/sendVideoStatus/videoMessageReveiveData";
         //String url = "http://127.0.0.1:8089/gateway/detectionLink/detectionLinkStatus";
         //String url = "http://119.23.65.189:8088/gateway/detectionLink/detectionLinkStatus";

         //开发服务器的公网接口调用  小程序端
        String url = "http://127.0.0.1:8090/monitoring/detectionLink/detectionLinkStatus";

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        // 使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        httpPost.addHeader("text/plain", "charset=UTF-8");
        String jsonbody = getSendJsonss();
        StringEntity entity = new StringEntity(jsonbody, "utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        response.setHeader("text/plain", "charset=UTF-8");
        System.out.println("response-------" + response);
        System.out.println("response.getStatusLine()====" + response.getStatusLine());
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
            String responseEntityStr = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("响应参数：" + responseEntityStr);
            System.out.println("Response content length: " + resEntity.getContentLength());
        }
        EntityUtils.consume(resEntity);

    }

    /**
     * 模拟测试返回下行状态的服务端
     *
     * @return
     */
    public static String getSendJsonss() {
        JSONObject json_param = new JSONObject();

        //政企客户编号
        String siID = "C10032";
        json_param.put("SiID", siID);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        String key = "FydfySgrdyet";
        String authenticator = siID + date + key;
        authenticator = MD5(authenticator);
        //根据平台分配的Key 进行分散，Authenticator=Md5(SiID+Date+Key),32 位大写
        json_param.put("Authenticator", authenticator);

        //时间戳,格式YYYY-MM-DD HH:mm:ss
        json_param.put("Date", date);

        JSONArray content = new JSONArray();
        JSONObject json1 = new JSONObject();
        json1.put("MsgID", "5cda29b617171");
        json1.put("Phone", "18906577722");
        json1.put("State", "RECEIVD");
        json1.put("TransID", "341056390200061558430776b1U16");
        json1.put("Amount", "Amount");
        content.add(json1);
        json_param.put("ReportList", content);
        String JSONBody = json_param.toString();

        return JSONBody;
    }



    /**
     * 联调方提供的加密方法
     *
     * @param s
     * @return
     */
    private static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * MD5加密嵌套方法---联调方提供
     *
     * @param bytes
     * @return
     */
    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    @Test
    void test(){
        BigDecimal bigDecimal = new BigDecimal(1);
        BigDecimal bigDecima2 = new BigDecimal(5);
        System.out.println(bigDecima2.compareTo(bigDecimal));

    }
}
