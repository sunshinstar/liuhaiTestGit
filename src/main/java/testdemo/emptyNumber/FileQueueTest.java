package testdemo.emptyNumber;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.Test;
import testdemo.emptyNumber.utils.DetectionRequestEntity;
import testdemo.emptyNumber.utils.HttpUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author liuhai
 * @date 2019/12/13 18:31
 */
class FileQueueTest {

    @Test
    void test1() throws Exception {
        DetectionRequestEntity detectionRequestEntity = new DetectionRequestEntity();
        detectionRequestEntity.setId("18722fd7d3e34594b768c30e9d1ce55c");
        detectionRequestEntity.setConstantField("12321321321321");
        detectionRequestEntity.setKey("122321321");
        detectionRequestEntity.setMobiles("17681874926,17681874926");
        detectionRequestEntity.setReceiptAddress("www.baidu.com");
        detectionRequestEntity.setTime(System.currentTimeMillis() + "");
        String dirPath = "F:\\monitorFolder";
        String fileName = detectionRequestEntity.getId();
        String filePath = dirPath + File.separator + fileName + ".txt";
        FileUtil.mkdir(dirPath);
        FileUtil.touch(filePath);
        //打开文件对象输出流  将对象写入到文件
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        objectOutputStream.writeObject(detectionRequestEntity);
        objectOutputStream.close();
        //打开文件对象输入流  从文件里面序列化读出对应的对象
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
        DetectionRequestEntity stu = (DetectionRequestEntity) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(stu.toString());
        //获取文件夹下面所有的文件名称
        List<String> fileNames = FileUtil.listFileNames(dirPath);
        fileNames.forEach(System.out::println);


    }

    @Test
    void test2() {
        JSONObject object = new JSONObject();
        String time = System.currentTimeMillis() + "";
        object.put("key", SecureUtil.md5("miaodi" + time));
        object.put("time", time);
        String result = null;
        try {
            result = Request.Post("/es/queryMobileStatus").body(new StringEntity(object.toString(), "utf-8"))
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }


    @Test
    void test3() {
        JSONObject object = new JSONObject();
        String time = System.currentTimeMillis() + "";
        object.put("key", SecureUtil.md5("miaodi" + time));
        object.put("time", time);
        String result = null;
        try {
            result = Request.Post("http://119.23.65.189/web/smsRequest/chargeBalanceAdjust").body(new StringEntity(object.toString(), "utf-8"))
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }


    /**
     * 语音接口测试
     */
    @Test
    void test4() {
        String appId = "10001";
        String developerId = "9002680";
        String businessId = "1";
        String price = "7500";
        String voiceSk = "2e3eeb7ea3a34b7caec3ecc247e97362";
        // 调语音平台
        Map<String, String> map = new HashMap<>(4);
        String url = "http://localhost:8080/externalDemand/unitPrice";
//        String url = "http://119.23.65.189/web/externalDemand/unitPrice";
        List<String> signParamsList = new ArrayList<>(map.size());
        Long now = System.currentTimeMillis();
        signParamsList.add(appId);
        signParamsList.add(developerId);
        signParamsList.add(businessId);
        signParamsList.add(price);
        signParamsList.add(now.toString());
        String sign = getSign(voiceSk, signParamsList);
        map.put("appId", appId);
        map.put("developerId", developerId);
        map.put("businessId", businessId);
        map.put("price", price);
        map.put("timestamp", now.toString());
        map.put("sign", sign);
        try {
            String result = HttpUtils.doPost(url, map);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void test5(){


    }


    public String getSign(String sk, List<String> list) {
        Collections.sort(list);
        StringBuilder sb = new StringBuilder(sk);
        for (String str : list) {
            if (ObjectUtil.isNotNull(str)) {
                sb.append(str);
            }
        }
        String sign = SecureUtil.md5(sb.toString());
        return sign;
    }

}
