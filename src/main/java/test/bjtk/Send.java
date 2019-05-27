package test.bjtk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
 * @date 2019/4/28 15:01
 */
public class Send {

    private static String url = "http://120.27.12.60:18002/send.do";

    private static String uid = "6881";

    private static String pw = "737493";

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
        String result = Util.doPost(url, map);
        System.out.println(result);
    }

    @Test
    public void test() {

        Map<String, Object> map = new HashMap<>();
        String userid = "mdlt";
        map.put("ua", userid);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        String tm = fmt.format(new Date());
        String pass = "737493";
        // String passwd = Util.getMD5(pass + tm);
        map.put("pw", pass);
        map.put("number", 500);
        String url = "http://120.27.12.60:8080/dx/GetReport";
        String result = Util.doPost(url, map);
        System.out.println(result);
    }

    /**
     * 推送过来的下行数据demo解析，上行不需要专门解析，因为上行的单条数据直接解析的
     * 北京天肯返回的下行状态demo解析
     */
    @Test
    public void test10() {
        String result = "0951409300200003,17681874926,DELIVRD|0951409120900002,17681874926,DELIVRD\n" +
                "|0951409120900002,17749518352,DELIVRD|0951409300200003,17749518352,BLACK\n" +
                "|0951409415500004,17749518352,BLACK|0951409415500004,17681874926,DELIVRD";
        String[] results = result.split("\\|");
        String[] resultData;
        for (int i = 0; i < results.length; i++) {
            resultData = results[i].split(",");
            for (int j = 0; j < resultData.length; j++) {
                System.out.println(resultData[j]);
            }
        }
    }


    //=========================================================================================================================
    //下面的两个主动获取接口暂时不用了，因为快乐网视不支持这两个接口，只支持推送模，不支持获取


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
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("ua", "mdlt"));
        paramList.add(new BasicNameValuePair("pw", "737493"));
        paramList.add(new BasicNameValuePair("number", "500"));
        String resultStr = Request.Post("http://120.27.12.60:8080/dx/GetMo").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        System.out.println("resultStr" + resultStr);
    }


    /**
     * 上面两个接口返回的数据是json数组格式的，因此需要双层解析的
     * json数组解析
     */
    @Test
    public void test9() {
        String result = "{\n" +
                "    \"SiID\": \"34105639020006\", \n" +
                "    \"Authenticator\": \"FFD4A81CBCF238CD6618B1F1BE4DF735\", \n" +
                "    \"Date\": \"2019-05-21 17:28:19\", \n" +
                "    \"ReportList\": [\n" +
                "        {\n" +
                "            \"MsgID\": \"5cda29b617171\", \n" +
                "            \"Phone\": \"18906577722\", \n" +
                "            \"State\": \"RECEIVD\", \n" +
                "            \"TransID\": \"341056390200061558430776b1U16\", \n" +
                "            \"Amount\": 1842\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONArray array = JSONArray.parseArray(result);
        if (null != array && array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = array.getJSONObject(i);
                System.out.println(object.getString("SiID"));
                System.out.println(object.getString("Authenticator"));
                System.out.println(object.getString("Date"));
            }
        }
    }

}
