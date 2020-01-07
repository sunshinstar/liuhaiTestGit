package operaDemo.utils;


import java.net.HttpURLConnection;
import java.util.*;

/**
 * @author liuhai
 * @date 2019/11/11 11:05
 */
public class Cookie {
    protected Map<String, String> map;
    protected boolean debug;

    public Cookie(String cookieString) {
        this.map = new HashMap();
    }

    public String get(String name) {
        return (String)this.map.get(name);
    }

    public Cookie remove(String name) {
        this.map.remove(name);
        return this;
    }

    public Cookie set(String name, String value) {
        this.map.put(name, value);
        return this;
    }


    @Override
    public String toString() {
        if (this.map.isEmpty()) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator var2 = this.map.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry<String, String> en = (Map.Entry)var2.next();
                sb.append((String)en.getKey()).append('=').append((String)en.getValue()).append("; ");
            }

            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
    }

    public static String[] splitIgnoreBlank(String s, String regex) {
        if (null == s) {
            return null;
        } else {
            String[] ss = s.split(regex);
            List<String> list = new LinkedList();
            String[] var4 = ss;
            int var5 = ss.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String st = var4[var6];
                if (!isBlank(st)) {
                    list.add(trim(st));
                }
            }

            return (String[])list.toArray(new String[list.size()]);
        }
    }



    public static String trim(CharSequence cs) {
        if (null == cs) {
            return null;
        } else {
            int length = cs.length();
            if (length == 0) {
                return cs.toString();
            } else {
                int l = 0;
                int last = length - 1;

                int r;
                for(r = last; l < length && Character.isWhitespace(cs.charAt(l)); ++l) {
                }

                while(r > l && Character.isWhitespace(cs.charAt(r))) {
                    --r;
                }

                if (l > r) {
                    return "";
                } else {
                    return l == 0 && r == last ? cs.toString() : cs.subSequence(l, r + 1).toString();
                }
            }
        }
    }


    public static boolean isBlank(String str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for(int i = 0; i < length; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }


    public void afterConnect(HttpURLConnection conn) {
        if (!this.map.isEmpty()) {
            String c = this.toString();
            if (this.debug) {
            }

            conn.addRequestProperty("Cookie", c);
        }
    }

    public Map<String, String> getMap() {
        return this.map;
    }

    public int size() {
        return this.map.size();
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
