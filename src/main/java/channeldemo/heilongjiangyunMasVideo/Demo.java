package channeldemo.heilongjiangyunMasVideo;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author liuhai
 * @date 2019/9/3 17:55
 */
public class Demo {


    @Test
    void test1() throws IOException {
        String str = "1234567890111";
        int n = 4;
        System.out.println(str.substring(str.length()-n));
    }


    public static void main(String[] args) throws Exception {
        Login();

    }
    public static void Login() throws Exception {
        String url="http://120.227.5.17:9001/sendVsms";
        String SendResult;
        SendResult=SendSms(url,GetParams());
        System.out.println("发送结果："+SendResult);
    }

    public static String GetParams() throws Exception{

        Long requestTime =System.currentTimeMillis();
        Long sentTime=null;
        Long tempId=(long) 202424;

        Submit submit=new Submit();
        //接口账号       @
        submit.setAppKey("c410f075");
        //请求时间Long  @
        submit.setTs(requestTime);
        //模板编号Long   @
        submit.setModeId(tempId);
        //15203082676  小麦     13433835760  伟廷    19865768325 嘉明
        String[] var={"19865768325","15203082676"};
        //手机号  @
        submit.setMobiles(var);
        //通知结果地址
        submit.setNotifyUrl("http://119.23.65.189/sms-tool/yunMac/sms/urlEncodedReceipt");
        //发送时间Long
        submit.setSendTime(sentTime);

        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(submit.getModeId());
        stringBuffer.append(submit.getTs());
        stringBuffer.append(submit.getAppKey());
        stringBuffer.append("2a43e445f64d8c5b");
        System.out.println("拼接值："+stringBuffer);

        //MD5加密
        String signMac=encryptToMD5(stringBuffer.toString());
        System.out.println("MD5加密值："+signMac);
        //签名  @
        submit.setSign(signMac);

        String param= JSON.toJSONString(submit);
        System.out.println("JSON数据:"+param);

        return param;
    }

    private static String SendSms(String url, String param) {
        OutputStreamWriter out = null;

        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("contentType","utf-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String encryptToMD5(String password) {
        byte[] digesta = null;
        String result = null;
        try {
            // 得到一个MD5的消息摘要   信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdi = MessageDigest.getInstance("MD5");
            // 添加要进行计算摘要的信息     MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdi.update(password.getBytes("utf-8"));
            // 得到该摘要     摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            digesta = mdi.digest();
//			System.out.println("md5加密后密文："+digesta);
            result = byteToHex(digesta);
        } catch (NoSuchAlgorithmException e) {

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将二进制转化为16进制字符串
     */
    public static String byteToHex(byte[] pwd) {
        StringBuilder hs = new StringBuilder("");
        String temp = "";
        for (int i = 0; i < pwd.length; i++) {
            temp = Integer.toHexString(pwd[i] & 0XFF);
            if (temp.length() == 1) {
                hs.append("0").append(temp);
            } else {
                hs.append(temp);
            }
        }
        return hs.toString().toLowerCase();
    }

}
class Submit{
    String appKey;
    Long   ts;
    Long   modeId;
    String sign;
    String[] mobiles;
    String notifyUrl;
    Long   sendTime;

    public Submit() {
        super();
    }
    public Submit(String appKey,  Long ts,
                  Long modeId, String sign, String[] mobiles, String notifyUrl,
                  Long sendTime) {
        super();
        this.appKey = appKey;

        this.ts = ts;
        this.modeId = modeId;
        this.sign = sign;
        this.mobiles = mobiles;
        this.notifyUrl = notifyUrl;
        this.sendTime = sendTime;
    }
    public String getAppKey() {
        return appKey;
    }
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
    public Long getTs() {
        return ts;
    }
    public void setTs(Long ts) {
        this.ts = ts;
    }
    public Long getModeId() {
        return modeId;
    }
    public void setModeId(Long modeId) {
        this.modeId = modeId;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String[] getMobiles() {
        return mobiles;
    }
    public void setMobiles(String[] mobiles) {
        this.mobiles = mobiles;
    }
    public String getNotifyUrl() {
        return notifyUrl;
    }
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
    public Long getSendTime() {
        return sendTime;
    }
    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }
}
