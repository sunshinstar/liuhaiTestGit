package operaDemo;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import operaDemo.utils.HttpRequest;
import operaDemo.utils.Response;
import operaDemo.utils.SendEmailAndSmsResponse;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author liuhai
 * @date 2019/11/11 9:42
 */
public class demo {


    public static void main(String[] args) {
        String channelId = "1065551605";
        String channelSecret = "7CF6CB5B77954352B87F316844D739C85D15FE02163748B48CB802C3EC9016D4";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("appId", "39nu467h5sia7op04bci2eo7ve");
        map.put("emailTemplateId", "7k6nm3eg9gjq2qkjjvhrpj8132");
        map.put("smsTemplateId", "0620db5b9343437c8d0f450bcab110cd");
        map.put("smsTemplateContext", "{'content':'您好，您的验证码是888888'}");
        map.put("recipient", "17681874926");
        map.put("expandCode", "520121");
        Long timeMillis = System.currentTimeMillis();
        map.put("timestamp", timeMillis + "");
        String sign = sortMap(channelSecret, map);
        HttpRequest request = new HttpRequest("http://120.52.8.230:18080/OpenEngine/open/push/sendNcEmailAndSmsTask.n", "POST");
        request.connectTimeout(60000);
        request.readTimeout(60000);
        request.header("Content-Type", "multipart/form-data; boundary=00content0boundary00");
        request.header("Cookie", "");
        request.header("timestamp", timeMillis);
        request.header("Authorization", channelId + ":" + sign);
        request.header("Accept", "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
        //不知道什么方法，反正不报错
        map.forEach(request::part);
        Response response = new Response(request);
        SendEmailAndSmsResponse sendEmailAndSmsResponse = JSON.parseObject(response.getContent(), SendEmailAndSmsResponse.class);
        System.out.println(JSON.toJSONString(sendEmailAndSmsResponse));
    }


    /**
     * 下发短信接口  只发送短信 不发送邮件
     */
    @Test
    void test1() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("appId", "5pp8o52jrsh9op7p626d9a6fh0");
        map.put("smsTemplateId", "3a1113353032489a85f34d69b31f77da");
        map.put("smsTemplateContext", "{'content':'您好，您的验证码是888888'}");
        map.put("smsRecipient", "17681874926");
        Long timeMillis = System.currentTimeMillis();
        map.put("timestamp", timeMillis + "");
        String secret = sortMap("7CF6CB5B77954352B87F316844D739C85D15FE02163748B48CB802C3EC9016D4", map);
        //进行发送并获取返回结果
        SendEmailAndSmsResponse sendEmailAndSmsResponse = getSendEmailAndSmsResponse("", map, timeMillis, secret);
        System.out.println(JSON.toJSONString(sendEmailAndSmsResponse));
    }

    private static String sortMap(String secretKey, TreeMap<String, String> map) {
        StringBuffer sb = new StringBuffer();
        //所有参与传参的参数按照accsii排序（升序）
        Set es = map.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();

            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(k + "=" + v);
        }
        return SecureUtil.md5(sb + secretKey).toUpperCase();
    }


    /**
     * jar包封装的发送方法，直接获取发送返回结果
     *
     * @param threadBO
     * @param map
     * @param timeMillis
     * @param secret
     * @return
     */
    private SendEmailAndSmsResponse getSendEmailAndSmsResponse(String threadBO, TreeMap<String, String> map, Long timeMillis, String secret) {
        HttpRequest request = new HttpRequest("http://120.52.8.230:18080/OpenEngine/open/sms/sendTask.n", "POST");
        request.connectTimeout(60000);
        request.readTimeout(60000);
        request.header("Content-Type", "=multipart/form-data;charset=UTF-8");
        request.header("Cookie", "");
        request.header("timestamp", timeMillis);
        request.header("Authorization", "1065551605" + ":" + secret);
        request.header("Accept", "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
        //不知道什么方法，反正不报错
        map.forEach(request::part);
        Response response = new Response(request);
        String content = response.getContent();
        System.out.println(content);
        return JSON.parseObject(content, SendEmailAndSmsResponse.class);
    }


}
