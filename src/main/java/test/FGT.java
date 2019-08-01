package test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.junit.jupiter.api.Test;
import channeldemo.videomessage.HttpUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author liuhai
 * @date 2019/6/10 19:38
 */
public class FGT {


    @Test
    public void test1() throws Exception {
        Map map = new HashMap(5);
        map.put("user", "miaodiltwd");
        map.put("pwd", "miaodiltwd");
        map.put("phone", "17681874926");
        map.put("serial", "1314520");
        map.put("msgcont", "【金岛公司】尊敬的各位领导：您好！我是广西百色金岛商务服务有限公司凌云分公司的客服代表，我公司是专门负责代理记账、工商服务、纳税申报等商务服务。感谢您一路的支持与厚爱，现我公司已按照原计划正与贵县110家村民合作社签订农村集体经济代理记账合同，费用为2000元/年.家，总值为22万元。我公司将拿出其中5.5万元的费用资助您当地凌云县的贫困学子（我公司将返回500元到每一家合作社），贫困学子的名单将由贵地提供给我公司，届时将由相关媒体做新闻报道，该方案已报备凌云县组织部。欢迎来电咨询17707765564，0776-2889889，相信我们能让您省钱更省心，金岛公司祝您工作生活愉快。回T退订。\n");
        String result = sendGet("http://qd.qxt666.cn/interface/tomsg.jsp", map);
        System.out.println(result);
    }

    @Test
    public void test8() {
        Map map = new HashMap(5);
        map.put("user", "miaodiltxxk");
        map.put("pwd", "miaodiltxxk");
        String result = sendGet("http://qd.qxt666.cn/interface/qamount.jsp", map);
        System.out.println(result);
    }

    /**
     * 迈远余额测试接口
     */
    @Test
    public void test11() {
        Map map = new HashMap(5);
        map.put("userid", "391");
        String timeSr = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        map.put("timestamp", timeSr);
        map.put("action", "overage");
        map.put("sign", SecureUtil.md5("mdkjfc123456" + timeSr));
        String result = HttpUtils.post("http://222.187.223.31:8868/sms.aspx", map);
        try {
            Map map1= doXMLParse(result);
            System.out.println(map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = "11112222";
        StringUtils.replace(name,"11","3333");
        System.out.println(name);
        System.out.println("返回结果为：" + result);
    }

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public  Map doXMLParse(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if(null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if(children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        //关闭流
        in.close();

        return m;
    }

    /**
     * 获取子结点的xml
     * @param children
     * @return String
     */
    public  String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    @Test
    public void test9() {
        String result = "17681874968,REFUSE,1314520,20190611153201,1710756886;17681874926,REFUSE,1314520,20190611153201,1710756888;17681874969,REFUSE,1314520,20190611153201,1710756887";
        String[] results = result.split(";");
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
    }

    @Test
    public void test10() {
        Integer num = 9;

        //进行计算时隐含的有自动拆箱
        System.out.print(num--);
        System.out.print(num);
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, Map<String, String> param) {
        String result = "";
        BufferedReader in = null;
        try {
            StringBuffer query = new StringBuffer();

            for (Map.Entry<String, String> kv : param.entrySet()) {
                query.append(URLEncoder.encode(kv.getKey(), "GBK") + "=");
                query.append(URLEncoder.encode(kv.getValue(), "GBK") + "&");
            }
            if (query.lastIndexOf("&") > 0) {
                query.deleteCharAt(query.length() - 1);
            }

            String urlNameString = url + "?" + query.toString();
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
