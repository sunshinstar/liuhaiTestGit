package channeltest.klws;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;

/**
 * @author o_0sky
 * @date 2019/4/28 15:01
 */
public class Util {

    /**
     * MD5加密
     *
     * @param s
     * @return
     */
    public static String getMD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    /**
     * http请求
     *
     * @param httpUrl
     * @param param
     * @return
     */
    public static String doPost(String httpUrl, Map<String, Object> param) {
        try {
            // 创建连接
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.connect();
            // POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(getDataStr(param).getBytes());
            out.flush();
            out.close();
            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer strBuffer = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                strBuffer.append(lines);
            }
            reader.close();
            connection.disconnect();
            return strBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 拼接post参数
     *
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String getDataStr(Map<String, Object> map) throws UnsupportedEncodingException {
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, Object> data : map.entrySet()) {
            if (null != data.getKey() && !"".equals(data.getKey()) && null != data.getValue() && !"".equals(data.getValue())) {
                if (data.getKey().equals("ms")) {
                    params.append("&").append(data.getKey()).append("=").append(URLEncoder.encode((String) data.getValue(), "utf-8"));
                } else {
                    params.append("&").append(data.getKey()).append("=").append(data.getValue());
                }
            }
        }
        return params.toString().substring(1, params.length());
    }

}
