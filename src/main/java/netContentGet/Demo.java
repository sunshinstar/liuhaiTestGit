package netContentGet;

import org.apache.commons.io.IOUtils;
import testdemo.emptyNumber.utils.ToolUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author liuhai
 * @date 2020/3/17 9:17
 */
public class Demo {

    public static void main(String[] args) {
        String url = "https://vayh.msxf.com/vspread/SXH5_dxz02/060012/53000047-10004";
        System.out.println(getContent2(url));
//        System.out.println(getContent(url));
    }

    public static String getContent2(String realUrl) {
        String data = "";
        StringBuffer stringBuffer = new StringBuffer();
        try {
            //创建一个URL实例
            URL url = new URL(realUrl);
            try {
                //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");

                //为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                //读取数据
                data = br.readLine();
                //循环读取数据
                while (data != null) {
                    //输出数据
                    stringBuffer.append(data).append("\n");
                    data = br.readLine();
                }
                br.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * 获取网址的内容
     *
     * @param urls
     * @return
     */
    public static String getContent(String urls) {
        URL url;
        int responseCode;
        HttpURLConnection urlConnection;
        BufferedReader reader;
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String urlCharset = getEncodingByMeta(urls);
            if (ToolUtil.isEmpty(urlCharset)) {
                urlCharset = getEncodingByHeader(urls);
            }
            if (ToolUtil.isEmpty(urlCharset)) {
                urlCharset = "utf-8";
            }
            //生成一个URL对象，要获取源代码的网页地址为：http://www.sina.com.cn
            url = new URL(urls);
            //打开URL
            urlConnection = (HttpURLConnection) url.openConnection();
            //获取服务器响应代码
            responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                //得到输入流，即获得了网页的内容
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), urlCharset));
                while ((line = reader.readLine()) != null) {
                    stringBuffer.append(line);
                }
            } else {
            }
        } catch (Exception e) {
        }
        return stringBuffer.toString();
    }

    /**
     * 从meta中获取页面编码
     *
     * @param strUrl
     * @return
     */
    private static String getEncodingByMeta(String strUrl) {
        String charset = null;
        try {
            URLConnection urlConn = new URL(strUrl).openConnection();
            //避免被拒绝
            urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            // 将html读取成行,放入list
            List<String> lines = IOUtils.readLines(urlConn.getInputStream());
            for (String line : lines) {
                if (line.contains("http-equiv") && line.contains("charset")) {
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

    /**
     * 从header中获取页面编码
     *
     * @param strUrl
     * @return
     */
    private static String getEncodingByHeader(String strUrl) {
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


}
