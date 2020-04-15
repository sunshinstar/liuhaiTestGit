package testdemo.xunfeiVoice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuhai
 * @date 2020/3/5 15:36
 */
public class VoiceSend1 {

    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(20);

        String fileName = "C:\\Users\\Administrator\\Desktop\\a\\20200222.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        String data = ",body:";
        String url = "https://gateway.smyfinancial.com:8443/b2b-front/callback/rcfService/mdVoicePushBack";
        int i = 0;
        while ((line = reader.readLine()) != null) {
            System.out.println(i++);
            int index = line.indexOf(data);
            String content = line.substring(index + data.length());
            service.execute(() -> {
                try {
                    JSONObject jsonObject = JSON.parseObject(content);
                    String called = jsonObject.getString("called");
                    jsonObject.put("callee",called);
                    String resultContent = sendPostJson(url, jsonObject.toJSONString(), 8000, 15000);
                    System.out.println(resultContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static String sendPostJson(String url, String param, int connectiTimeOut, int readTimeOut) throws IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            java.net.URL realUrl = new java.net.URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json");
            // 设置超时时间和读取时间
            conn.setConnectTimeout(connectiTimeOut);
            conn.setReadTimeout(readTimeOut);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
        return result;
    }

}
