package channeldemo.netdress;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author liuhai
 * @date 2019/8/4 18:42
 */
public class Demo {

    public static void main(String[] args) throws IOException {

        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("address", "www,ceshi.com"));
        paramList.add(new BasicNameValuePair("name", "刘海测试"));
        String resultStr = Request.Post("http://47.112.139.195:9096/add_target").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        System.out.println("resultStr" + resultStr);

    }

    @Test
    void test1()  {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address","www.liuhai.com");
        jsonObject.put("name","刘海测试");
        String resultStr = null;
        try {
            resultStr = Request.Post("http://47.112.139.195:9096/add_target").body(new StringEntity(jsonObject.toString(), "UTF-8"))
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("resultStr" + resultStr);
    }

    /**
     * 对接的时候要用这个方法去对接
     */
    @Test
    void test2()  {
        String resultStr = null;
        try {
            resultStr = Request.Post("http://47.112.139.195:9096/add_target?address=www.baidu.com/&name=www.baidu.com/").body(null)
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("resultStr" + resultStr);
    }


    /**
     * 对接的时候要用这个方法去删除
     */
    @Test
    void test3()  {
        String resultStr = null;
        try {
            resultStr = Request.Post("http://47.112.139.195:9096/delete_target?address=121").body(null)
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("resultStr" + resultStr);
    }

    /**
     * 截取字符串里面的网址
     */
    @Test
    void Test4(){
            String data = "【金猪传奇】到账提醒：68888元宝，正版传奇超高爆率！30分钟12转满级，领VIP9http://t.cn/EXr3Cub1/ 13回T退订";
            Matcher matcher = Patterns.WEB_URL.matcher(data);
            if (matcher.find()){
                System.out.println(matcher.group());
            }
    }


    /**
     *模拟告警数据
     */
    @Test
    void test5()  {
        String resultStr = null;
        try {
            resultStr = Request.Post("http://119.139.196.139:8091/monitoring/detectionLink/detectionLinkStatus?address=1212&msg=sadsda").body(null)
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("resultStr" + resultStr);
    }


    @Test
    void test6(){
        List list =splitHttp("https://weex.kaola.com/activity/pages/jk0iubmlpagesc.html?tag=_ta_zhuanke_03&__da_dad3e203_592c1e770e033c80");
        System.out.println(list);
    }


    @Test
    void test7(){
        String data = "{\"err\":0,\"msg\":\"\"}";
        JSONObject jsonObject = (JSONObject) JSON.parse(data);
        System.out.println(jsonObject.get("err"));
    }

    public static List<String> splitHttp(String content){
        List<String> strList = new ArrayList<>();
        //截取字符串
        String[] urls = content.split("http");
        if (urls.length > 1) {
            for (int j = 1; j < urls.length; j++) {
                StringBuilder sb = new StringBuilder();
                //以http为首，拼接起来逐步校验每个字符
                String splitContent = "http" + urls[j];
                for (int i = 0; i < splitContent.length(); i++) {
                    String str = splitContent.substring(i, i + 1);
                    if (isUrlChar(str)) {
                        sb.append(str);
                    } else {
                        break;
                    }
                }
                strList.add(sb.toString());
            }
        }
        return strList;
    }

    /**
     * 正则校验网址
     * @param str
     * @return
     */
    private static boolean isUrlChar(String str) {
        String regex = "^[a-z0-9A-Z:/.?&=_]+$";
        return str.matches(regex);
    }
}
