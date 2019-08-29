package channeldemo.yunmac;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuhai
 * @date 2019/8/1 18:15
 */
public class YunMacController {

    @Test
    void test1(){
        String resultJson = "{\n" +
                "    \"totalData\": {\n" +
                "        \"successTotal\": 0, \n" +
                "        \"failTotal\": 0, \n" +
                "        \"sendTotal\": 20000, \n" +
                "        \"failedTotal\": 0, \n" +
                "        \"platformRejectTotal\": 20000, \n" +
                "        \"unknownTotal\": 0, \n" +
                "        \"submitFailTotal\": 0\n" +
                "    }, \n" +
                "    \"analyzeArrayData\": {\n" +
                "        \"analyzeData\": [\n" +
                "            {\n" +
                "                \"smsCount\": \"10000\", \n" +
                "                \"resCode\": \"0023\"\n" +
                "            }\n" +
                "        ], \n" +
                "        \"failTotal\": 10000\n" +
                "    }, \n" +
                "    \"developerId\": \"103884\", \n" +
                "    \"rate\": 22, \n" +
                "    \"threshold\": 111\n" +
                "}\n";
        JSONObject jsonObject = JSON.parseObject(resultJson);
        JSONObject jsonObject1 = JSON.parseObject(jsonObject.get("failTotal").toString());
        JSONArray jsonArray = (JSONArray)JSONArray.parse(jsonObject1.get("analyzeData").toString());
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.println(jsonArray.get(i));
        }
    }


    @Test
    void test2(){
        System.out.println(  format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
    }
    public  String format(long time, String format) {
        if (time == 0) {
            return null;
        }
        Date date = new Date();
        date.setTime(time);
        return new SimpleDateFormat(format).format(date);
    }
}
