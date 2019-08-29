package channeldemo.videomessage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试
 *
 * @author liuhai
 * @date no
 */
public class HttpPostsTest {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 设置请求和传输超时时间
     */
    private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();


    /**
     * c尝试给自己发http接口并且进行解析，可以实现   将数据封装进UrlEncodedFormEntity
     *
     * @throws IOException
     */
    @Test
    public void test5() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String URL = "http://localhost:8089/gateway/sendVideoStatus";
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setConfig(requestConfig);
        List formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("Phone", "中国"));
        formParams.add(new BasicNameValuePair("Amount", "value2"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println("response-------" + response);
    }

    /**
     * c尝试给自己发http接口并且进行解析，可以实现     StringEntity
     *
     * @throws IOException
     */
    @Test
    void test6() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //下发状态的接口地址
        //String url ="http://miaodi.f3322.net:7089/gateway/sendVideoStatus/videoMessageReveiveData";
        //String url ="http://119.23.65.189:8088/gateway/sendVideoStatus/videoStatusReveiveData";
        //String url = "http://127.0.0.1:8089/gateway/sendVideoStatus/videoStatusReveiveData";
        //String url ="http://192.168.11.1:8089/gateway/sendVideoStatus/videoStatusReveiveData";
        //公网地址  廷永配置的
        String url = "http://47.112.97.97:8580/dxvapi/report";
        //模板审核状态的地址
        //String url = "http://47.112.97.97:8580/dxvapi/templateStatus";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        // 使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        httpPost.addHeader("text/plain", "charset=UTF-8");
        String jsonbody = getSendJsonss();
        StringEntity entity = new StringEntity(jsonbody, "utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        response.setHeader("text/plain", "charset=UTF-8");
        System.out.println("response-------" + response);
        System.out.println("response.getStatusLine()====" + response.getStatusLine());
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
            String responseEntityStr = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("响应参数：" + responseEntityStr);
            System.out.println("Response content length: " + resEntity.getContentLength());
        }
        EntityUtils.consume(resEntity);
    }

    @Test
    public void test8() {
        String url = "http://127.0.0.1:8089/gateway/sendVideoStatus/videoMessageReveiveData";
        String str = HttpUtils.post(url, getSendJsonss());
        System.out.println(str);
        System.out.println();
    }


    /**
     * 带参下发接口  (暂未完成，参数含义不明确)
     * 客户使用已经审核通过的素材,但素材中部分文字需要替换，则需要在下发的同时告
     * 知网关，由网关替换后再下发给用户。
     */
    @Test
    public void test4() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            String URL = "http://47.100.172.112:10005/option";
            HttpPost httpPost = new HttpPost(URL);
            httpPost.setConfig(requestConfig);
            //默认的Content-Type是text/plain
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("text/plain", "charset=UTF-8");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).
                    build();
            httpPost.setConfig(requestConfig);
            File file = new File("E:\\1.jpg");
            // 判断文件是否存在
            if (!file.exists()) {
                System.out.print("文件不存在");
                return;
            }
            File file1 = new File("E:\\1.mp4");
            // 判断文件是否存在
            if (!file.exists()) {
                System.out.print("文件不存在");
                return;
            }
            //设置封装实体  将文件封装到apache的封装文件包下
            // application/x-jpg	// image/jpeg
            FileBody bin = new FileBody(file);
            // application/x-jpg	//mp4
            FileBody bin1 = new FileBody(file1);
            String jsonParam = getJSONParam3();
            System.out.println(jsonParam);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 测试发送简单的视频短信
     * 普通下发接口
     */
    @Test
    public void test() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            String URL = "http://47.100.172.112:10005/sapi/send";
            //String URL = "http://124.126.120.102:8896/sapi/send";
            HttpPost httpPost = new HttpPost(URL);
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-Type", "application/json");
            String JSONBody = getSendJson();
            StringEntity sEntity = new StringEntity(JSONBody, "utf-8");
            httpPost.setEntity(sEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            System.out.println("response-------" + response);
            try {
                System.out.println("response.getStatusLine()===" + response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String responseEntityStr = EntityUtils.toString(response.getEntity());
                    System.out.println("响应参数：" + responseEntityStr);
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                //1 关闭应该关闭的资源，适当的释放资源 2也可以把底层的流给关闭了
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * 素材下发接口(这个接口不开放了，也就是无法使用了以后)
     * 客户下发未提交审核的素材，并同时告知下发的用户信息，又后台进行自动审核后，
     * 直接想用户发送内容
     */
    @Test
    public void test1() {
        //使用帮助类HttpClients创建CloseableHttpClient对象.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            String url = "http://47.100.172.112:10005/multiSend";
            //基于要发送的HTTP请求类型创建HttpPost实例.
            HttpPost httppost = new HttpPost(url);
            // 使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
            httppost.addHeader("text/plain", "charset=UTF-8");
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000)
                    .build();
            httppost.setConfig(requestConfig);
            File file = new File("E:\\1.jpg");
            // 判断文件是否存在
            if (!file.exists()) {
                System.out.print("文件不存在");
                return;
            }
            File file1 = new File("E:\\1.mp4");
            // 判断文件是否存在
            if (!file.exists()) {
                System.out.print("文件不存在");
                return;
            }
            // application/x-jpg	// image/jpeg
            FileBody bin = new FileBody(file);
            // application/x-jpg	//mp4
            FileBody bin1 = new FileBody(file1);
            String jsonParam = getJSONParam2();
            System.out.println("素材下发时候的请求json参数：" + jsonParam);
            StringBody comment = new StringBody(jsonParam, ContentType.APPLICATION_JSON);
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("Data", comment).
                    addPart("1.jpg", bin).addPart("1.mp4", bin1).build();
            httppost.setEntity(reqEntity);
            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            System.out.println("response=====" + response);
            try {
                System.out.println("response.getStatusLine()====" + response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String responseEntityStr = EntityUtils.toString(response.getEntity());
                    System.out.println("响应参数：" + responseEntityStr);
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 测试上传附件功能（素材提交接口）
     *
     * @param args
     */
    public static void main(String[] args) {
        //使用帮助类HttpClients创建CloseableHttpClient对象.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 测试接口
            String url = "http://47.100.172.112:10005/sapi/material";
            //正式接口
            //String url = "http://124.126.120.102:8896/sapi/material";
            //基于要发送的HTTP请求类型创建HttpPost实例.
            HttpPost httppost = new HttpPost(url);
            // 使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
            httppost.addHeader("text/plain", "charset=UTF-8");
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000)
                    .build();
            httppost.setConfig(requestConfig);

            File file = new File("E:\\1.jpg");
            // 判断文件是否存在
            if (!file.exists()) {
                System.out.print("文件不存在");
                return;
            }
            File file1 = new File("E:\\1.mp4");
            // 判断文件是否存在
            if (!file.exists()) {
                System.out.print("文件不存在");
                return;
            }
            //设置封装实体  将文件封装到apache的封装文件包下
            // application/x-jpg	// image/jpeg
            FileBody bin = new FileBody(file);
            // application/x-jpg	//mp4
            FileBody bin1 = new FileBody(file1);
            String jsonParam = getJSONParam();
            System.out.println("请求参数：" + jsonParam);
            StringBody comment = new StringBody(jsonParam, ContentType.APPLICATION_JSON);

            //MultipartEntityBuilder实现类似form表单提交方式的文件上传
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("Data", comment).
                    addPart("1.jpg", bin).addPart("1.mp4", bin1).build();
            httppost.setEntity(reqEntity);
            //获取的结果类似   请求方式请求地址以及请求协议   POST http://47.100.172.112:10005/materialUpload HTTP/1.1
            System.out.println("executing request ===" + httppost.getRequestLine());
            //通过执行HttpPost请求获取CloseableHttpResponse实例
            CloseableHttpResponse response = httpclient.execute(httppost);

            System.out.println("response===" + response);
            try {
                // 得到http响应结果   response.getStatusLine().getStatusCode()  获取请求的状态
                System.out.println("response.getStatusLine()===" + response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String responseEntityStr = EntityUtils.toString(response.getEntity());
                    System.out.println("响应参数：" + responseEntityStr);
                    //resEntity.getContentLength() 获取的是返回值的长度
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                //1 关闭应该关闭的资源，适当的释放资源 2也可以把底层的流给关闭了
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 联调方提供的加密方法
     *
     * @param s
     * @return
     */
    private static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * MD5加密嵌套方法---联调方提供
     *
     * @param bytes
     * @return
     */
    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }


    /**
     * 获取上传附件的json组织串
     *
     * @return
     */
    public static String getJSONParam() {
        JSONObject json_param = new JSONObject();

        //政企客户编号
        String siID = "C10032";
        json_param.put("SiID", siID);

        //秘钥key
        String key = "FydfySgrdyet";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        String authenticator = siID + date + key;
        //加密参数      根据平台分配的Key 进行分散，Authenticator=Md5(SiID+Date+Key)
        authenticator = MD5(authenticator);
        json_param.put("Authenticator", authenticator);

        //时间   时间戳,格式YYYY-MM-DD HH:mm:ss
        json_param.put("Date", date);

        //方法    'material':上传素材
        String method = "material";
        json_param.put("Method", method);

        //扩展号码    扩展号码，接入号码（后台配置）+扩展号码总长度小于等于20 位
        String extNum = "10691415";
        json_param.put("ExtNum", extNum);

        //素材主题  素材主题,建议长度小于等于9
        String subject = "秒嘀测试";
        json_param.put("Subject", subject);

        //上传的内容信息,主要包含帧数，帧内顺序，扩展名，文件名等信息
        JSONArray content = new JSONArray();
        json_param.put("Content", content);

        JSONObject json1 = new JSONObject();
        String frame = "1-1";
        String text = "【本条信息免流量费，发送TD退订】";
        //Frame    该字段代表文件的顺便，其中“-”前面的数字代表第几帧，“-”后面的数字代笔该文件在这个帧内的顺序
        json1.put("Frame", frame);
        /*Text    文本内容，当附件为文本类型时使用该字段，内容编码:UTF-8当内容中含有可变参数时，使用$进行包裹，例如：尊敬的$person$您好。
        则下发接口需要使用option 类型，Content 中的Param 则为{person:'李先生'}*/
        json1.put("Text", text);
        content.add(json1);

        JSONObject json2 = new JSONObject();
        frame = "2-1";
        String fileName = "1.jpg";
        json2.put("Frame", frame);
        //FileName     文件名，注：附件为音频、视频、图片时使用该字段
        json2.put("FileName", fileName);
        content.add(json2);

        JSONObject json3 = new JSONObject();
        frame = "3-1";
        fileName = "1.mp4";
        json3.put("Frame", frame);
        json3.put("FileName", fileName);
        content.add(json3);

        return json_param.toString();
    }


    /**
     * 素材下发接口的json字符串拼接
     *
     * @return
     */
    private String getJSONParam2() {
        JSONObject json_param = new JSONObject();
        //政企客户编号
        String siID = "C10032";
        json_param.put("SiID", siID);

        //秘钥key
        String key = "FydfySgrdyet";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        String authenticator = siID + date + key;
        //加密参数
        authenticator = MD5(authenticator);
        json_param.put("Authenticator", authenticator);

        //当前日期
        json_param.put("Date", date);

        //方法
        String method = "multi";
        json_param.put("Method", method);

        //要发送的手机号码
        String phines[] = {"17352854043"};
        json_param.put("Phones", phines);

        //扩展号码
        String extNum = "10691415";
        json_param.put("ExtNum", extNum);

        //素材主题
        String subject = "test";
        json_param.put("Subject", subject);
        JSONArray content = new JSONArray();
        JSONObject json1 = new JSONObject();
        //Frame   该字段代表文件的顺便，其中'-'前面的数字代表第几帧，'-'后面的数字代笔该文件在这个帧内的顺序
        String frame = "1-1";
        String text = "测试发送信息，退订回复T，此条信息免流量费\n";
        json1.put("Frame", frame);
        //Text    文本内容，当附件为文本类型时使用该字段，内容编码:UTF-8
        json1.put("Text", text);
        content.add(json1);

        JSONObject json2 = new JSONObject();
        frame = "2-1";
        String fileName = "1.jpg";
        json2.put("Frame", frame);
        //FileName    文件名，注：附件为音频、视频、图片时使用该字段
        json2.put("FileName", fileName);
        content.add(json2);

        JSONObject json3 = new JSONObject();
        frame = "2-2";
        fileName = "1.mp4";
        json3.put("Frame", frame);
        json3.put("FileName", fileName);
        content.add(json3);

        json_param.put("Content", content);

        return json_param.toString();
    }


    /**
     * 带参下发接口json字符串拼接
     *
     * @return
     */
    private String getJSONParam3() {
        JSONObject json_param = new JSONObject();

        //政企客户编号
        String siID = "";
        json_param.put("SiID", siID);

        //秘钥key
        String key = "FydfySgrdyet";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        String authenticator = siID + date + key;
        //加密参数      根据平台分配的Key 进行分散，Authenticator=Md5(SiID+Date+Key)
        authenticator = MD5(authenticator);
        json_param.put("Authenticator", authenticator);

        //时间   时间戳,格式YYYY-MM-DD HH:mm:ss
        json_param.put("Date", date);

        //方法    'material':上传素材
        String method = "option'";
        json_param.put("Method", method);

        //用户手机号码列表，最长200 个，最短1 个
        String phones[] = {"17681874926"};
        json_param.put("Phones", phones);

        //媒体消息内容ID
        String mshid = "a3dc0cffe8b37ef09a0b682fe1e1ce12";
        json_param.put("MsgID", mshid);


        //上传的内容信息,主要包含帧数，帧内顺序，扩展名，文件名等信息
        JSONArray content = new JSONArray();
        json_param.put("Content", content);

        JSONObject json1 = new JSONObject();
        String frame = "1-1";
        String param = "测试发送信息，退订回复T，此条信息免流量费\n";
        //Frame    该字段代表文件的顺便，其中“-”前面的数字代表第几帧，“-”后面的数字代笔该文件在这个帧内的顺序
        json1.put("Frame", frame);
        /*Text    文本内容，当附件为文本类型时使用该字段，内容编码:UTF-8当内容中含有可变参数时，使用$进行包裹，例如：尊敬的$person$您好。
        则下发接口需要使用option 类型，Content 中的Param 则为{person:'李先生'}*/
        json1.put("Param", param);
        content.add(json1);

        JSONObject json2 = new JSONObject();
        frame = "2-1";
        String fileName = "1.jpg";
        json2.put("Frame", frame);
        //FileName     文件名，注：附件为音频、视频、图片时使用该字段
        json2.put("Param", fileName);
        content.add(json2);

        JSONObject json3 = new JSONObject();
        frame = "2-2";
        fileName = "1.mp4";
        json3.put("Frame", frame);
        json3.put("Param", fileName);
        content.add(json3);

        String JSONBody = json_param.toString();
        return JSONBody;
    }

    /**
     * 普通发视频短信的json组织串
     *
     * @return
     */
     String getSendJson() {
        JSONObject json_param = new JSONObject();

        //政企客户编号
        String siID = "C10032";
        json_param.put("SiID", siID);

        String date = format.format(new Date());
        String key = "FydfySgrdyet";
        String authenticator = siID + date + key;
        authenticator = MD5(authenticator);
        //根据平台分配的Key 进行分散，Authenticator=Md5(SiID+Date+Key),32 位大写
        json_param.put("Authenticator", authenticator);

        // 方法  'send'
        String method = "send";
        json_param.put("Method", method);

        //时间戳,格式YYYY-MM-DD HH:mm:ss
        json_param.put("Date", date);

        //用户手机号码列表，最长200 个，最短1 个
        //博文  17702712078
        //俊江    18923359384
        //陈高  18926576813
        //张晓林   18126154325
        //曾春焜   18124521865
        String phones[] = {"17702712078","18923359384"};
        json_param.put("Phones", phones);

        //媒体消息内容ID
        String mshid = "5d649243qH97c";
        json_param.put("MsgID", mshid);

        String JSONBody = json_param.toString();

        return JSONBody;
    }


    /**
     * 模拟测试返回下行状态的服务端
     *
     * @return
     */
    public String getSendJsonss() {
        JSONObject json_param = new JSONObject();

        //政企客户编号
        String siID = "C10032";
        json_param.put("SiID", siID);

        String date = format.format(new Date());
        String key = "FydfySgrdyet";
        String authenticator = siID + date + key;
        authenticator = MD5(authenticator);
        //根据平台分配的Key 进行分散，Authenticator=Md5(SiID+Date+Key),32 位大写
        json_param.put("Authenticator", authenticator);

        //时间戳,格式YYYY-MM-DD HH:mm:ss
        json_param.put("Date", date);

        JSONArray content = new JSONArray();
        JSONObject json1 = new JSONObject();
        json1.put("MsgID", "5cda29b617171");
        json1.put("Phone", "18906577722");
        json1.put("State", "RECEIVD");
        json1.put("TransID", "341056390200061558430776b1U16");
        json1.put("Amount", "Amount");
        content.add(json1);
        json_param.put("ReportList", content);
        String JSONBody = json_param.toString();

        return JSONBody;
    }


}
