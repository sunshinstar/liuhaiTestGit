package channeldemo.ZQSX;

import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MapSort {
    /**
     * @Title: sortMap
     * @Description: 对集合内的数据按key的字母顺序做排序
     */
    public static Map<String, Object> sortMap(String secretKey, String apiUrl, Map<String, Object> map) {
        final List<Map.Entry<String, Object>> infos = new ArrayList<>(map.entrySet());

        // 重写集合的排序方法：按字母顺序
        Collections.sort(infos, (o1, o2) -> {
            return (o1.getKey().toString().compareTo(o2.getKey().toString()));
        });

        StringBuffer signStr = new StringBuffer(secretKey).append(apiUrl).append("?");
        for (Map.Entry<String, Object> entry : infos) {
            String key = entry.getKey();
            if (!StringUtils.equals(key, "sign") && !StringUtils.equals(key, "param")) {
                signStr.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }

        String substring = signStr.substring(0, signStr.lastIndexOf("&"));
        map.put("sign", SecureUtil.md5(substring));
        return map;
    }
}
