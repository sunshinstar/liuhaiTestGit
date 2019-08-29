package channeldemo.qianliankeji;

import cn.hutool.core.codec.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhai
 * @date 2019/8/21 16:53
 */
public class TestDemoMobil {

    private static final String HEN = "|";
    private static final String FEN = ";";


    /**
     * 发送接口   可以调的通
     */
    @Test
    void sent() {
        try {
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("action", "send"));
            paramList.add(new BasicNameValuePair("userid", "2765"));
            paramList.add(new BasicNameValuePair("account", "qlkj106cx"));
            paramList.add(new BasicNameValuePair("password", "qlkj123456"));
            //移动号码    嘉明  19865768325
            paramList.add(new BasicNameValuePair("mobile", "19865768325"));
            paramList.add(new BasicNameValuePair("title", "【温馨提示】威高七彩城"));
            paramList.add(new BasicNameValuePair("starttime", ""));
            paramList.add(new BasicNameValuePair("extno", "1314520"));
            File file1 = new File("E:\\0.mp4");
            String file = Base64.encode(file1);
            String tex = "尊敬的会员：8月10日[云端系]样板盛大开放，建面约110-180㎡王座全城热推！限时惊喜折扣，诚邀品鉴！威高地产13载开发经验重塑威海改善标准-威高七彩城：原生海岸万亩松林、7大城市级配套敬献威海新贵、3亿级威高海洋馆、1200㎡社区图书馆、约3.7万㎡社区公园、800米松林跑道、50%绿化率和桃源水系、5H生命滋养科技系统，打造西海岸高端大城！项目地址：威海高区世昌大道和文化西路交会\\ 项目电话：0631-3898888";
            String text = Base64.encode(tex, "GB2312");
            paramList.add(new BasicNameValuePair("content", "1,txt|" + text + ",mp4|" + file + ";2,txt|" + text));
            String resultStr = null;
            try {
                resultStr = Request.Post("http://119.29.200.194:6687/sendmms.aspx").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(resultStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询余额接口    可以调通
     */
    @Test
    void test2() {
        try {
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("userid", "2765"));
            paramList.add(new BasicNameValuePair("account", "qlkj106cx"));
            paramList.add(new BasicNameValuePair("password", "qlkj123456"));
            paramList.add(new BasicNameValuePair("action", "overage"));
            String resultStr = Request.Post("http://119.29.200.194:6687/sendmms.aspx").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
            System.out.println(resultStr);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * 状态查询接口
     */
    @Test
    void test3() {
        try {
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("userid", "2765"));
            paramList.add(new BasicNameValuePair("account", "qlkj106cx"));
            paramList.add(new BasicNameValuePair("password", "qlkj123456"));
            paramList.add(new BasicNameValuePair("statusNum", ""));
            paramList.add(new BasicNameValuePair("action", "query"));
            String resultStr = Request.Post("http://119.29.200.194:6687/mmsStatusApi.aspx").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
            System.out.println(resultStr);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * 测试编码
     */
    @Test
    void test() {
        File file1 = new File("E:\\2.mp4");
        String file = Base64.encode(file1);
        System.out.println(file);
        String tex = "欧莱雅集团是法国的化妆品公司，创办于1970年欧莱雅集团是美妆行业的领导者，经营范围遍及130多个国家和地区，在全球拥有283家公司、42家工厂、100多个代理商，以及五万 多名的员工";
        try {
            String text = Base64.encode(tex, "GB2312");
            System.out.println(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void test6() {

    }
}

