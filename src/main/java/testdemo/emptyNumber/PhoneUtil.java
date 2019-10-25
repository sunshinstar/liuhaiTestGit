package testdemo.emptyNumber;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号码
 *
 * @author qinquan
 */
public class PhoneUtil {

    private static Object lock = new Object();
    public static List<String> yiDongList = new ArrayList<String>();
	public static List<String> lianTongList = new ArrayList<String>();
	public static List<String> dianXinList = new ArrayList<String>();

	public static void update(List<String> yiDong, List<String> lianTong, List<String> dianXin){
	   synchronized(lock){
		   yiDongList.clear();
		   lianTongList.clear();
		   dianXinList.clear();
		   yiDongList.addAll(yiDong);
		   lianTongList.addAll(lianTong);
		   dianXinList.addAll(dianXin);
	   }
    }

    /**
     * 是否移动手机号码
     *
     * @param phone
     * @return 是移动返回true
     */
	public static boolean isYiDong(String phone) {
        if (phone == null || phone.length() < 11) {
            return false;
        }
        if (phone.length() > 11) {
            return true;
        }
        synchronized(lock){
        	return yiDongList.contains(phone.substring(0, 3)) || yiDongList.contains(phone.substring(0, 4));
        }
    }

    /**
     * 是否联通手机号码
     *
     * @param phone
     * @return 是联通返回true
     */
    public static boolean isLianTong(String phone) {
        if (phone == null || phone.length() < 11){
            return false;
        }
        synchronized(lock){
        	return lianTongList.contains(phone.substring(0, 3)) || lianTongList.contains(phone.substring(0, 4));
        }
    }

    /**
     * 是否电信手机号码
     *
     * @param phone
     * @return 是电信返回true
     */
    public static boolean isDianXin(String phone) {
        if (phone == null || phone.length() < 11){
        	return false;
        }
        synchronized(lock){
        	return dianXinList.contains(phone.substring(0, 3)) || dianXinList.contains(phone.substring(0, 4));
        }    
    }

    /**
     * 是否是手机号码(简单版，以1开头并且是十一位数字）
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneSimple(String phone) {
        Pattern pat = PatternUtil.getPattern("^1[0-9]{10}");
        Matcher matcher = pat.matcher(phone);
        return matcher.matches();
    }

	public static boolean getaVarPhone(String phone,List<String> phonePrefixList) {
		for (String phonePrefix : phonePrefixList) {
			int length = phonePrefix.length();
            if (length > phone.length()) {
				return false;
			}
			if(StringUtils.equals(phone.substring(0, length),phonePrefix)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 校验手机号格式
	 * @param phone
	 * @return
	 */
	public static boolean isPhoneFormat(String phone){
		// 是否是11位数字
		Pattern pat = PatternUtil.getPattern("^[0-9]{11}");
		Matcher matcher = pat.matcher(phone);
		boolean is11Digit = matcher.matches();
		if (!is11Digit) {
			return false;
		}
		return true;
	}

	/**
	 * 校验运营商
	 * @param phone
	 * @return
	 */
	public static boolean isOperator(String phone){
		// 前三位是否是运营商号段
		if(PhoneUtil.getaVarPhone(phone,PhoneUtil.yiDongList)){
			return true;
		}
		if(PhoneUtil.getaVarPhone(phone,PhoneUtil.lianTongList)){
			return true;
		}
		if(PhoneUtil.getaVarPhone(phone,PhoneUtil.dianXinList)){
			return true;
		}
		return false;
	}

	/**
	 * 是否是手机号码(高级版）
	 *
	 * @param phone
	 * @return
	 */
	public static boolean isPhoneAdvanced(String phone) {
		boolean bool = false;
		// 是否是11位数字
		Pattern pat = PatternUtil.getPattern("^[0-9]{11}");
		Matcher matcher = pat.matcher(phone);
		boolean is11Digit = matcher.matches();
		if (!is11Digit) {
			return false;
		}
		return true;
	}

	/**
	 * 判断运营商
	 * @param phone
	 * @return
	 */
	public static String isOperatorType(String phone){
		boolean bool = false;

		// 前三位是否是运营商号段
		bool = PhoneUtil.getaVarPhone(phone,PhoneUtil.yiDongList);
		if(bool){
			return "1";
		}
		bool = PhoneUtil.getaVarPhone(phone,PhoneUtil.lianTongList);
		if(bool){
			return "3";
		}
		bool = PhoneUtil.getaVarPhone(phone,PhoneUtil.dianXinList);
		if(bool){
			return "2";
		}
		return "0";
	}

    public static List<String> getYiDong(){
    	return yiDongList;
    }
    public static List<String> getLianTong(){
    	return lianTongList;
    }
    public static List<String> getDianXin(){
    	return dianXinList;
    }
}