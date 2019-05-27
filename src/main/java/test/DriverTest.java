package test;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author o_0sky
 * @date 2019/4/23 11:44
 */
public class DriverTest {

    /**
     * 盟信互通接口发送信息
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("Account", "MDEL"));
        paramList.add(new BasicNameValuePair("Password", "abcd1234"));
        paramList.add(new BasicNameValuePair("Phones", "17681874926"));
        paramList.add(new BasicNameValuePair("Channel", "1"));
        paramList.add(new BasicNameValuePair("Content", "【秒嘀科技】 您的状态码是8888，回T退订！"));
        paramList.add(new BasicNameValuePair("SendTime", ""));
        String resultStr = Request.Post("http://119.23.154.247:8000/SendSms.asp").body(new UrlEncodedFormEntity(paramList, "GB2312")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("GB2312"));
        System.out.println(resultStr);
    }

    /**
     * 盟信互通获取短信发送状态报告  下行记录
     *
     * @throws IOException
     */
    @Test
    public void test1() throws Exception {
        while (true) {

            Thread.sleep(10000);
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("Account", "MDEL"));
            paramList.add(new BasicNameValuePair("Password", "abcd1234"));
            String resultStr = Request.Post("http://119.23.154.247:8000/GetReport.asp").body(new UrlEncodedFormEntity(paramList, "GB2312")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("GB2312"));
            System.out.println("resultStr" + resultStr);
        }

    }

    /**
     * 盟信互通下行记录解析demo
     *
     * @throws IOException
     */
    @Test
    public void test6() {
        String resultStr = "177888$$$$17681874926$$$$2019/4/28 18:01:06$$$$成功$$$$DELIVRD||||177889$$$$17681874926$$$$2019/4/28 18:01:53$$$$成功$$$$DELIVRD";
        String[] result = resultStr.split("\\|\\|\\|\\|");
        String[] results;
        for (int i = 0; i < result.length; i++) {
            results = result[i].split("\\$\\$\\$\\$");
            System.out.println(results.length);
            for (int j = 0; j < results.length; j++) {
                System.out.println(results[j]);
            }
        }
    }

    /**
     * 盟信互通获取短信上行记录
     *
     * @throws IOException
     */
    @Test
    public void test11() throws IOException {

        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("Account", "MDEL"));
        paramList.add(new BasicNameValuePair("Password", "abcd1234"));
        String resultStr = Request.Post("http://119.23.154.247:8000/GetMessage.asp").body(new UrlEncodedFormEntity(paramList, "GB2312")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("GB2312"));
        System.out.println("resultStr" + resultStr);

    }

    /**
     * 盟信互通上行返回结果解析demo
     *
     * @throws IOException
     */
    @Test
    public void test4() {
        String resultStr = "17681874926$$$$2$$$$2019/4/28 18:02:13";
        String[] result = resultStr.split("\\|\\|\\|\\|");
        String[] results;
        for (int i = 0; i < result.length; i++) {
            results = result[i].split("\\$\\$\\$\\$");
            System.out.println(results.length);
            for (int j = 0; j < results.length; j++) {
                System.out.println(results[j]);
            }
        }
    }


    /**
     * 中正云通信发送信息接口 带扩展码的，不带扩展码的不要了
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("uid", "miaodijy2"));
        paramList.add(new BasicNameValuePair("pwd", "miaodijy2"));
        paramList.add(new BasicNameValuePair("tos", "17681874926"));
        paramList.add(new BasicNameValuePair("msg", "【教育资讯】您好！根据“湖州市关于推进中小学生研学旅行的实施意见”，我们研发的农耕文化研学课程将于下周末（5月11日）开课。本课程让孩子带着主题游玩，在玩乐中探索学习。由专家老师执教，围绕教学大纲，让孩子轻松学习。有兴趣可拨打400-1155-866咨询。回T退订\n "));
        paramList.add(new BasicNameValuePair("otime", ""));
        //暂时不传扩展码了，因为那边最多只支持五位，没法用的
        // paramList.add(new BasicNameValuePair("extno", "0123456789"));
        String resultStr = Request.Post("http://service2.winic.org/service.asmx/SendMessages").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        System.out.println("resultStr" + resultStr);
        //0507151102809552
    }

    /**
     * 中正云通信 获取短信下行报告
     *
     * @throws IOException
     */
    @Test
    public void tests() throws IOException {
        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("uid", "miaodijy2"));
        paramList.add(new BasicNameValuePair("pwd", "miaodijy2"));
        String resultStr = Request.Post("http://service2.winic.org/service.asmx/SMS_Reports").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        System.out.println("resultStr" + resultStr);
    }

    /**
     * 中正云通信 获取短信上行报告  不带扩展的
     *
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("uid", "miaodijy2"));
        paramList.add(new BasicNameValuePair("pwd", "miaodijy2"));
        //端口号可以为空，对接的时候说的，不需要指定
        paramList.add(new BasicNameValuePair("IDtype", ""));
        String resultStr = Request.Post("http://service2.winic.org/service.asmx/GET_SMS_MO").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        System.out.println("resultStr" + resultStr);
    }

    /**
     * 中正云返回数据数据解析demo
     *
     * @throws DocumentException
     */
    @Test
    public void test7() throws DocumentException {
        Document document = DocumentHelper.parseText("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<string xmlns=\"http://tempuri.org/\">null</string>");
        // 获取根节点
        Element rootElt = document.getRootElement();
        System.out.println("根节点数据：" + rootElt.getStringValue());
        String[] sys = rootElt.getStringValue().split("\\|");
        System.out.println(sys.length);
        if (rootElt.getStringValue().indexOf("null") != -1) {
            System.out.println("111111111111111111");
        }
        for (int i = 0; i < sys.length; i++) {
            String[] result = sys[i].split("\\/");
            System.out.println(result.length);
            for (int j = 0; j < result.length; j++) {
                System.out.println(result[j]);
            }
        }
    }

}


