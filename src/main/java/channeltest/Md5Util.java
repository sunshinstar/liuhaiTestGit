package channeltest;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 */
public class Md5Util {

    private static final String DEFAULT_ENCODING = "GB2312";
    
    /**
     * 
     * 
     * @param source
     * @return
     */
    public static String toMd5(String source) {
        EncryptUtilMd5 eu = new EncryptUtilMd5();
        String md5 = eu.toMD5(source);
        return md5;
    }

    /**
     * 
     * 
     * @param input
     * @return
     */
    public static String MD5(String input){
        return MD5(input, DEFAULT_ENCODING);
    }
    
    /**
     * 
     * 
     * @param text
     * @param enocding
     * @return
     */
    public static String MD5(String text, String enocding){
        if (StringUtils.isNotBlank(text)){
            
            /*
             * �������Ϊ�գ�ʹ��Ĭ�ϱ���
             */
            if(StringUtils.isBlank(enocding)){
                enocding = DEFAULT_ENCODING;
            }
            
            try {
                byte[] output = MessageDigest.getInstance("MD5").digest(text.getBytes(enocding));
                StringBuffer out = new StringBuffer();
                
                for (int i = 0; i < output.length; i++) {
                    String t = Integer.toHexString(output[i] & 0x00FF);
                    out.append((t.length() == 1) ? ("0" + t) : t);
                }
                
                return out.toString().toUpperCase();
            } catch (NoSuchAlgorithmException e) {
            } catch (UnsupportedEncodingException e) {
            }
        }
        return "";
    }
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

    }
}