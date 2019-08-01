package channeldemo.videomessage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhai
 * @date 2019/5/10 10:18
 */
public class Test {


    @org.junit.jupiter.api.Test
    public void test3() {
        List list = new ArrayList();
        List newList = list.subList(0, list.size() > 10 ? 10 : list.size());
        for (int i = 0; i < newList.size(); i++) {
            System.out.println("iiiiiiii" + newList.get(i) + "===" + i);
        }
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        BigDecimal s = new BigDecimal(0.00001).setScale(1, BigDecimal.ROUND_HALF_UP);
        System.out.println(s);
    }


    /**
     * 解析彩信供应商的下行回执
     */
    @org.junit.jupiter.api.Test
    public void test5() {
        String smsTemplateStr = "{\n" +
                "    \"SiID\": \"34105639020006\", \n" +
                "    \"Authenticator\": \"FFD4A81CBCF238CD6618B1F1BE4DF735\", \n" +
                "    \"Date\": \"2019-05-21 17:28:19\", \n" +
                "    \"ReportList\": [\n" +
                "        {\n" +
                "            \"MsgID\": \"5cda29b617171\", \n" +
                "            \"Phone\": \"18906577722\", \n" +
                "            \"State\": \"RECEIVD\", \n" +
                "            \"TransID\": \"341056390200061558430776b1U16\", \n" +
                "            \"Amount\": 1842\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Message mes = JSON.parseObject(smsTemplateStr, Message.class);
        System.out.println(mes.getReportList().get(0).getMsgID());

      /*  JSONObject jsonObject = JSON.parseObject(smsTemplateStr);
        System.out.println(jsonObject.get("SiID"));
        JSONArray jsonObject1= (JSONArray) jsonObject.get("ReportList");
        System.out.println(jsonObject1);*/
    }


    /**
     * 解析彩信供应商的下行回执
     */
    @org.junit.jupiter.api.Test
    public void test6() {
        String smsTemplateStr = "{\"SiID\":\"34105639020006\",\"Authenticator\":\"FFD4A81CBCF238CD6618B1F1BE4DF735\",\"Date\":\"2019-05-21 17:28:19\",\"ReportList\":[{\"MsgID\":\"5cda29b617171\",\"Phone\":\"18906577722\",\"State\":\"RECEIVD\",\"TransID\":\"341056390200061558430776b1U16\",\"Amount\":1842}]}";
        JSONObject jsonObject = JSON.parseObject(smsTemplateStr);
        System.out.println(jsonObject.get("Phone"));
        for (String str : jsonObject.keySet()) {
            System.out.println(str + "===========" + jsonObject.get(str));
        }
    }

    @org.junit.jupiter.api.Test
    public void test7() throws Exception {
        long startTime = System.currentTimeMillis();
        File f = new File("d:" + File.separator + "test.txt");
        FileInputStream fileInputStream = new FileInputStream(f);
        int content = 0; //声明该变量用于存储读取到的数据
        while ((content = fileInputStream.read()) != -1) {
            System.out.print((char) content);
        }
        fileInputStream.close();
        long endTime = System.currentTimeMillis();
        System.out.println("读取的时间是：" + (endTime - startTime));
    }


    @org.junit.jupiter.api.Test
    public void test121() throws  Exception{
        System.out.println("\u5f00\u53d1\u8005\u5229\u6da6\u7b49\u7ea7\u4fee\u6539");
    }

    public static void fileCopy(String source, String target) throws IOException {
        try (InputStream in = new FileInputStream(source)) {
            try (OutputStream out = new FileOutputStream(target)) {
                byte[] buffer = new byte[4096];
                int bytesToRead;
                while ((bytesToRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
            }
        }
    }
}
