package channeldemo.qianliankeji;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhai
 * @date 2019/8/22 17:36
 */
public class TestDemoUnit {


    /**
     * 联通发送接口
     */
    @Test
    void test1(){
        try {
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("userid", "2770"));
            paramList.add(new BasicNameValuePair("account", "qlkj10655sp"));
            paramList.add(new BasicNameValuePair("password", "qlkj123456"));
            paramList.add(new BasicNameValuePair("mobile", "17681874926"));
            paramList.add(new BasicNameValuePair("content", "【威高七彩城】尊敬的会员：8月10日[云端系]样板盛大开放，建面约110-180㎡王座全城热推！限时惊喜折扣，诚邀品鉴！威高地产13载开发经验重塑威海改善标准-威高七彩城：原生海岸万亩松林、7大城市级配套敬献威海新贵、3亿级威高海洋馆、1200㎡社区图书馆、约3.7万㎡社区公园、800米松林跑道、50%绿化率和桃源水系、5H生命滋养科技系统，打造西海岸高端大城！ 项目地址：威海高区世昌大道和文化西路交会 项目电话：0631-3898888"));
            paramList.add(new BasicNameValuePair("sendTime", ""));
            paramList.add(new BasicNameValuePair("action", "send"));
            paramList.add(new BasicNameValuePair("extno", "1314520"));
            String resultStr = Request.Post("http://119.29.200.194:6687/sms.aspx").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));


            System.out.println(resultStr);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    /**
     * 联通余额查询接口
     */
    @Test
    void test2(){
        try {
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("userid", "2770"));
            paramList.add(new BasicNameValuePair("account", "qlkj10655sp"));
            paramList.add(new BasicNameValuePair("password", "qlkj123456"));
            paramList.add(new BasicNameValuePair("action", "overage"));
            String resultStr = Request.Post("http://119.29.200.194:6687/sms.aspx").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
            System.out.println(resultStr);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    /**
     * 联通状态报告地址
     */
    @Test
    void test3(){
        try {
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("userid", "2770"));
            paramList.add(new BasicNameValuePair("account", "qlkj10655sp"));
            paramList.add(new BasicNameValuePair("password", "qlkj123456"));
            paramList.add(new BasicNameValuePair("statusNum", ""));
            paramList.add(new BasicNameValuePair("action", "query"));
            String resultStr = Request.Post("http://119.29.200.194:6687/statusApi.aspx").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
            System.out.println(resultStr);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    /**
     * 联通上行回复
     */
    @Test
    void test4(){
        try {
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("userid", "2770"));
            paramList.add(new BasicNameValuePair("account", "qlkj10655sp"));
            paramList.add(new BasicNameValuePair("password", "qlkj123456"));
            paramList.add(new BasicNameValuePair("action", "query"));
            String resultStr = Request.Post("http://119.29.200.194:6687/callApi.aspx").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
            System.out.println(resultStr);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
