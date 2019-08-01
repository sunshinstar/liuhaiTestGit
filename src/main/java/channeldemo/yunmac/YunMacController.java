package channeldemo.yunmac;


import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuhai
 * @date 2019/8/1 18:15
 */
public class YunMacController {

    @Test
    void test1(){

        String URL = "http://112.35.1.155:1992/sms/tmpsubmit";

        String ecName = "河南天道信息文化传播有限公司";
        String apId = "tdxx";
        String tempId = "5aa85e61ffd940cda888746e80c4014b";
        String mobiles = "17702712078";
        String[] params = new String[]{"859415"};
        String sign = "kpUrJYyJD";
        String addSerial = "";
        String secretKey = "tdxx07";
        String signStr = ecName + apId + secretKey + tempId + mobiles + JSON.toJSONString(params) + sign + addSerial;
        Map<String, Object> paramList = new HashMap<>(16);
        paramList.put("ecName", ecName);
        paramList.put("apId", apId);
        paramList.put("templateId", tempId);
        paramList.put("mobiles", mobiles);
        paramList.put("params", params);
        paramList.put("sign", sign);
        paramList.put("addSerial", addSerial);
        paramList.put("mac", SecureUtil.md5(signStr));
        String paramsStr = JSON.toJSONString(paramList);
        String result = HttpUtil.post(URL, Base64.encode(paramsStr));

        System.out.println(result);

    }

}
