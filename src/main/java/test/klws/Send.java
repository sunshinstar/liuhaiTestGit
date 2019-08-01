package test.klws;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author o_0sky
 * 快乐央视
 * @date 2019/4/28 15:01
 */
public class Send {

    private static String url = "http://101.251.236.60:18003/send.do";

    private static String uid = "9514";

    private static String pw = "606848";

    public static void main(String[] args) {
        String mb = "17681874926,17749518352";
        String message = "【秒嘀科技】测试信息，您的验证码是66666  回T退订";
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        String tm = fmt.format(new Date());
        send(mb, message, tm);
    }


    /**
     * 发送短信
     *
     * @param mb      手机号
     * @param message 短信内容
     * @param tm      当前时间
     */
    public static void send(String mb, String message, String tm) {
        if ("".equals(mb) || "".equals(message) || "".equals(tm)) {
            return;
        }

        String passwd = Util.getMD5(pw + tm);

        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("pw", passwd);
        map.put("mb", mb);
        map.put("ms", message);
        map.put("tm", tm);
        map.put("ex", "55555");
        map.put("dm", "");
        String result = Util.doPost(url, map);
        System.out.println(result);
    }


    /**
     * 北京天肯下行记录获取
     *
     * @throws IOException
     */
    @Test
    public void testBJTK1() throws IOException {
        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("ua", "mdlt"));
        paramList.add(new BasicNameValuePair("pw", "737493"));
        paramList.add(new BasicNameValuePair("number", "500"));
        String resultStr = Request.Post("http://120.27.12.60:8080/dx/GetReport").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        System.out.println("resultStr" + resultStr);
    }


    /**
     * 北京天肯上行记录获取
     *
     * @throws IOException
     */
    @Test
    public void testBJTK3() throws IOException {
        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("ua", "mdlt"));
        paramList.add(new BasicNameValuePair("pw", "737493"));
        paramList.add(new BasicNameValuePair("number", "500"));
        String resultStr = Request.Post("http://120.27.12.60:8080/dx/GetMo").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        System.out.println("resultStr" + resultStr);
    }

    /**
     * 北京天肯获取余额
     *
     * @throws IOException
     */
    @Test
    public void testBJTK5() throws IOException {
        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("uid", "9514"));
        paramList.add(new BasicNameValuePair("pw", "606848"));

        String resultStr = Request.Post("http://101.251.236.60:18005/balance.do")
                .body(new UrlEncodedFormEntity(paramList))
                .socketTimeout(60000)
                .connectTimeout(60000)
                .execute()
                .returnContent()
                .asString(Charset.forName("UTF-8"));
        System.out.println("resultStr: " + resultStr);

//        String params = "uid=9514&pw=6068478";
//        String s = Request.Get("http://101.251.236.60:18005/balance.do?uid=9514&pw=606848").socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("utf-8"));
//        System.out.println(s);
    }

}
