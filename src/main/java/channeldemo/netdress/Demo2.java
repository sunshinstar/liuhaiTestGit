package channeldemo.netdress;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author liuhai
 * @date 2019/8/8 15:15
 */
public class Demo2 {
    private static Set<String> set = new HashSet<>();
    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";
    private static final String WWW = "www.";


    public static void main(String[] args) {
        //0 - 126
        List<String> list = new ArrayList<>();
        list.add(".com");
        list.add(".cn");
        list.add(".net");
        list.add(".org");
        list.add("http");
        list.add(".aspx");

        String content = "【汤河口镇政府】你好啊,dwz.cn/8CH2MSgq";
        for (String s : list) {
            checkContent(content, s);
        }
        System.out.println(set);
    }

    /**
     * 迭代检测内容
     * @param content
     * @param s
     */
    private static void checkContent(String content, String s) {
        int index = content.indexOf(s);
        if (index != -1) {
            String url = processStr(content, index);
            if (null != url && !url.equals("")) {
                content = content.replaceAll(url, "");
                if (null != url && !url.equals("")) {
                    if (!url.contains(HTTP) && !url.contains(HTTPS) && !url.contains(WWW)) {
                        url = HTTP + url;
                    }
                    if (url.contains(WWW) && !url.contains(HTTPS) && !url.contains(HTTP)) {
                        url = HTTPS + url;
                    }
                    set.add(url);
                }
                checkContent(content, s);
            }
        }
    }

    public static String processStr(String content, int index) {
        int startIndex = 0;
        int endIndex = content.length();

        for (int i = index; i > startIndex; i--) {
            int code = (int) content.charAt(i);
            if (code < 0 || code > 126) {
                startIndex = i;
                break;
            } else if (!isUrlChar(content.charAt(i) + "")) {
                startIndex = i;
                break;
            }
        }

        for (int i = index; i < endIndex; i++) {
            int code = (int) content.charAt(i);
            if (code < 0 || code > 126) {
                endIndex = i;
                break;
            } else if (!isUrlChar(content.charAt(i) + "")) {
                endIndex = i;
                break;
            }
        }

        String url = content.substring(startIndex + 1, endIndex);
        return url;
    }

    public static boolean isUrlChar(String str) {
        String regex = "^[a-z0-9A-Z:/.?&=_]+$";
        return str.matches(regex);
    }
}
