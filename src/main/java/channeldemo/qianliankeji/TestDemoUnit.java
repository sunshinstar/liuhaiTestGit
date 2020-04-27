package channeldemo.qianliankeji;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.thread.ThreadUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author liuhai
 * @date 2019/8/22 17:36
 */
public class TestDemoUnit {


    /**
     * 联通发送接口
     */
    @Test
    void test1() {
        try {
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("userid", "1760"));
            paramList.add(new BasicNameValuePair("account", "深圳秒嘀视频0.13y"));
            paramList.add(new BasicNameValuePair("password", "123456"));
            paramList.add(new BasicNameValuePair("mobile", "17681874926"));
            paramList.add(new BasicNameValuePair("content", "【8848钛金手机】向成功者致敬"));
            paramList.add(new BasicNameValuePair("sendTime", ""));
            paramList.add(new BasicNameValuePair("action", "send"));
            paramList.add(new BasicNameValuePair("extno", "1314520"));
            String resultStr = Request.Post("http://123.56.219.245:8088/sms.aspx").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));


            System.out.println(resultStr);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    /**
     * 联通余额查询接口
     */
    @Test
    void test2() {
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
    void test3() {
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
     * 获取上行
     */
    @Test
    void getMo() {
        try {
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("userid", "1760"));
            paramList.add(new BasicNameValuePair("account", "深圳秒嘀视频0.13y"));
            paramList.add(new BasicNameValuePair("password", "123456"));
            paramList.add(new BasicNameValuePair("action", "query"));
            String resultStr = Request.Post("http://123.56.219.245:8088/callApi.aspx").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
            System.out.println(resultStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 联通上行回复    彩信没有上行
     */
    @Test
    void test4() {
        try {
            //创建缓存，默认4毫秒过期
            TimedCache<String, String> timedCache = CacheUtil.newTimedCache(4);
            //实例化创建
            //TimedCache<String, String> timedCache = new TimedCache<String, String>(4);
            //1毫秒过期
            timedCache.put("key1", "value1", 1);
            timedCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 5);
            //默认过期(4毫秒)
            timedCache.put("key3", "value3");
            //启动定时任务，每5毫秒秒检查一次过期
            timedCache.schedulePrune(5);

            //等待5毫秒
            ThreadUtil.sleep(5);

            //5毫秒后由于value2设置了5毫秒过期，因此只有value2被保留下来
            //null
            String value1 = timedCache.get("key1");
            //value2
            String value2 = timedCache.get("key2");

            //5毫秒后，由于设置了默认过期，key3只被保留4毫秒，因此为null
            //null
            String value3 = timedCache.get("key3");

            //取消定时清理
            timedCache.cancelPruneSchedule();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    void test5() {
        String data = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms>\n" +
                " <returnstatus>Success</returnstatus>\n" +
                " <message>ok</message>\n" +
                " <remainpoint>7</remainpoint>\n" +
                " <taskID>14857519</taskID>\n" +
                " <successCounts>1</successCounts></returnsms>";

        try {
            Map map = XMLUtil.doXMLParse(data);
            System.out.println(map.get("successCounts"));
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 联通关键词查询
     */
    @Test
    void test6() {
        try {
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("userid", "2770"));
            paramList.add(new BasicNameValuePair("account", "qlkj10655sp"));
            paramList.add(new BasicNameValuePair("password", "qlkj123456"));
            paramList.add(new BasicNameValuePair("action", "checkkeyword"));
            paramList.add(new BasicNameValuePair("content", ""));
            String resultStr = Request.Post("http://119.29.200.194:6687/sms.aspx").body(new UrlEncodedFormEntity(paramList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
            System.out.println(resultStr);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public List<Map<String, String>> doXMLParseList(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        List<Map<String, String>> xmlList = new ArrayList<>();

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Map<String, String> m = new HashMap();
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = XMLUtil.getChildrenText(children);
            }
            xmlList.add(XMLUtil.doXMLParse("<" + k + ">" + v + "</" + k + ">"));
        }
        //关闭流
        in.close();
        return xmlList;
    }


    @Test
    void test7() {
        try {
            ServerSocket sskt = new ServerSocket(6891);
            System.out.println("端口[" + 6891 + "]可用。");
        } catch (IOException e) {
            System.out.println("端口 [" + 6891 + "] 被占用.");
        }

        String msgId = "748:";
        String[] configStr = msgId.split(":");

        System.out.println(configStr.length);
    }
}
