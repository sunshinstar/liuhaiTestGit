package testdemo.urlMonitor;

import info.monitorenter.cpdetector.io.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.junit.jupiter.api.Test;
import testdemo.emptyNumber.utils.ToolUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuhai
 * @date 2019/12/20 9:40
 */
public class TestDemo {


    /**
     * 获取原始网址
     */
    @Test
    void test1() {
        HttpClient httpclient = new DefaultHttpClient();
        int responseCode;
        String url = "https://188992.com/";
        try {
            final HttpGet request = new HttpGet(url);
            HttpParams params = new BasicHttpParams();
            params.setParameter("http.protocol.handle-redirects", false);
            // 这样就能拿到Location头了
            request.setParams(params);
            HttpResponse response = httpclient.execute(request);
            responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == 302) {
                Header locationHeader = response.getFirstHeader("Location");
                if (locationHeader != null) {
                    url = locationHeader.getValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(url);
    }


    /**
     * 这个是根据域名来进行查询的
     */
    @Test
    void test2() throws UnknownHostException {
        //获取固定网址的ip和域名
        InetAddress ia2 = InetAddress.getByName("https://www.so.com/s?ie=utf-8&src=360chrome_toolbar_search&q=java.net.MalformedURLException");
        System.out.println(ia2.toString());
        //域名               127
        System.out.println(ia2.getHostName());
        //ip地址
        System.out.println(ia2.getHostAddress());

    }


    /**
     * 读取网页的文本
     *
     * @throws Exception
     */
    @Test
    void test3() throws Exception {
        URL url;
        int responsecode;
        HttpURLConnection urlConnection;
        BufferedReader reader;
        String line;
        try {
            String urls = "https://www.cnblogs.com/coder-lichao/p/10931919.html";
            String urlCharset = getEncodingByMeta(urls);
            if (ToolUtil.isEmpty(urlCharset)) {
                urlCharset = getEncodingByHeader(urls);
            }
            System.out.println(urlCharset);
            //生成一个URL对象，要获取源代码的网页地址为：http://www.sina.com.cn
            url = new URL(urls);
            //打开URL
            urlConnection = (HttpURLConnection) url.openConnection();
            //获取服务器响应代码
            responsecode = urlConnection.getResponseCode();
            if (responsecode == 200) {
                //得到输入流，即获得了网页的内容
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), urlCharset));
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } else {
                System.out.println("获取不到网页的源码，服务器响应代码为：" + responsecode);
            }
        } catch (Exception e) {
            System.out.println("获取不到网页的源码,出现异常：" + e);
        }
    }

    /**
     * 从header中获取页面编码
     *
     * @param strUrl
     * @return
     */
    public static String getEncodingByHeader(String strUrl) {
        String charset = null;
        try {
            URLConnection urlConn = new URL(strUrl).openConnection();
            // 获取链接的header
            Map<String, List<String>> headerFields = urlConn.getHeaderFields();
            // 判断headers中是否存在Content-Type
            if (headerFields.containsKey("Content-Type")) {
                //拿到header 中的 Content-Type ：[text/html; charset=utf-8]
                List<String> attrs = headerFields.get("Content-Type");
                String[] as = attrs.get(0).split(";");
                for (String att : as) {
                    if (att.contains("charset")) {
//                        System.out.println(att.split("=")[1]);
                        charset = att.split("=")[1];
                    }
                }
            }
            return charset;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return charset;
        } catch (IOException e) {
            e.printStackTrace();
            return charset;
        }
    }

    /**
     * 从meta中获取页面编码
     *
     * @param strUrl
     * @return
     */
    public static String getEncodingByMeta(String strUrl) {
        String charset = null;
        try {
            URLConnection urlConn = new URL(strUrl).openConnection();
            //避免被拒绝
            urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            // 将html读取成行,放入list
            List<String> lines = IOUtils.readLines(urlConn.getInputStream());
            for (String line : lines) {
                if (line.contains("http-equiv") && line.contains("charset")) {
//                    System.out.println(line);
                    String tmp = line.split(";")[1];
                    charset = tmp.substring(tmp.indexOf("=") + 1, tmp.indexOf("\""));
                } else {
                    continue;
                }
            }
            return charset;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return charset;
        } catch (IOException e) {
            e.printStackTrace();
            return charset;
        }
    }

    public static String getUrlCharset(String url) {
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            System.out.println("Content-Type" + "--->" + map.get("Content-Type"));
            List<String> list = map.get("Content-Type");
            if (list.size() > 0) {
                String contentType = list.toString().toUpperCase();
                if (contentType.contains("UTF-8")) {
                    return "UTF-8";
                }
                if (contentType.contains("GB2312")) {
                    return "GB2312";
                }
                if (contentType.contains("GBK")) {
                    return "GBK";
                }
            }
            //如果相应头里面没有编码格式,用下面这种
            CodepageDetectorProxy codepageDetectorProxy = CodepageDetectorProxy.getInstance();
            codepageDetectorProxy.add(JChardetFacade.getInstance());
            codepageDetectorProxy.add(ASCIIDetector.getInstance());
            codepageDetectorProxy.add(UnicodeDetector.getInstance());
            codepageDetectorProxy.add(new ParsingDetector(false));
            codepageDetectorProxy.add(new ByteOrderMarkDetector());
            Charset charset = codepageDetectorProxy.detectCodepage(new URL(url));
            return charset.name();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 根据网址查询ip
     */
    @Test
    void test4() throws IOException {
        URI uri = URI.create("http://q123.keqiaojz3.cn/");
        System.out.println(uri.getHost());
        InetAddress ia2 = InetAddress.getByName(uri.getHost());
        System.out.println(ia2.getHostAddress());
//        String resultStr = Request.Get("http://ip.taobao.com/service/getIpInfo.php?ip=" + ia2.getHostAddress()).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("GBK"));

//        System.out.println(resultStr);
    }


    /**
     * 获取网页的标题
     */
    @Test
    void test5() {
        URL url;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL("http://a.4399.cn/mobile/123845.html");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String temp;
            while ((temp = in.readLine()) != null) {
                sb.append(temp);
            }
            in.close();
        } catch (MalformedURLException e) {
            System.out.println("你输入的URL格式有问题！请仔细输入");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> list = new ArrayList<String>();
        String title = "";

        //Pattern pa = Pattern.compile("<title>.*?</title>", Pattern.CANON_EQ);也可以
        //源码中标题正则表达式
        Pattern pa = Pattern.compile("<title>.*?</title>");
        Matcher ma = pa.matcher(sb);
        while (ma.find())//寻找符合el的字串
        {
            list.add(ma.group());//将符合el的字串加入到list中
        }
        for (int i = 0; i < list.size(); i++) {
            title = title + list.get(i);
        }
        System.out.println(title.replaceAll("<.*?>", ""));
    }

    /**
     * 获取网页的所有的超链接
     */
    @Test
    void test6() {
        try {
            ArrayList<String> URLList = getWebInnerUrl("https://app.52067.app:520/m/m52067.html?shareName=1202cun300song88");
            URLList.forEach(s -> {
                System.out.println(s);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getWebInnerUrl(String htmlUrl) throws IOException {
        ArrayList<String> hrefList = new ArrayList();
        URL url = new URL(htmlUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        String urlCharset = getEncodingByMeta(htmlUrl);
        if (ToolUtil.isEmpty(urlCharset)) {
            urlCharset = getEncodingByHeader(htmlUrl);
        }
        if(ToolUtil.isEmpty(urlCharset)){
            urlCharset = "utf-8";
        }
        InputStreamReader isr = new InputStreamReader(connection.getInputStream(), urlCharset);
        BufferedReader br = new BufferedReader(isr);
        String str = null, rs = null;
        while ((str = br.readLine()) != null) {
            try {
                Pattern pattern = Pattern.compile("href=(.*?)>");
                Matcher matcher = pattern.matcher(str);

                while (matcher.find()) {
                    Pattern pattern1 = Pattern.compile("\"(.*?)\"");
                    Matcher matcher1 = pattern1.matcher(matcher.group(1));
                    if (matcher1.find()) {
                        rs = matcher1.group(1);
                    }
                    if (rs.indexOf("http") != -1) {
                        if (rs != null) {
                            hrefList.add(rs);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hrefList;
    }

}
