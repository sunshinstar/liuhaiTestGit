package channeldemo.netdress;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
            resultStr = Request.Post("http://127.0.0.1:9096/add_target").body(new StringEntity(jsonObject.toString(), "UTF-8"))
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
            resultStr = Request.Post("http://127.0.0.1:9096/add_target?address=http://www.abbao.cn/&name=www.baidu.com/").body(null)
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
            resultStr = Request.Post("http://127.0.0.1:9096/delete_target?address=http://t.cn/hfF2N").body(null)
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
            resultStr = Request.Post("http://sms.miaodiyun.com/monitoring/detectionLink/detectionLinkStatus?address=1212&msg=sadsda").body(null)
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
        String data = "%E5%A2%9E%E5%8A%A0124%E5%AD%97%2C%20%E5%87%8F%E5%B0%91155%E5%AD%97";
        try {
            System.out.println(URLDecoder.decode(data,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Test
    void test8(){
        List list= splitHttp("【马上消费金融】7天免息券到账！借款27500.00可免息7天，打开APP立即提现→ dwz.cn/8CH2MSgq 回T退");
        System.out.println(list);
    }




    public static List<String> splitHttp(String content){
        List<String> strList = new ArrayList<>();
        //截取字符串
        String[] httpUrls = content.split("http://");
        String[] httpsUrls = content.split("https://");
        getUrl(strList, httpUrls,"http://");
        getUrl(strList, httpsUrls,"https://");
        if(ToolUtil.isOneEmpty(httpUrls,httpsUrls)){
            String[] wwwUrls = content.split("www.");
            getUrl(strList, wwwUrls,"www.");
        }
        return strList;
    }

    private static void getUrl(List<String> strList, String[] urls,String urlRex) {
        if (urls.length > 1) {
            for (int j = 1; j < urls.length; j++) {
                StringBuilder sb = new StringBuilder();
                //以http为首，拼接起来逐步校验每个字符
                String splitContent = urlRex + urls[j];
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
