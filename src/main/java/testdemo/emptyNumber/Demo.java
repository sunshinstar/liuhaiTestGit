package testdemo.emptyNumber;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;
import testdemo.emptyNumber.utils.FileToBase64;
import testdemo.emptyNumber.utils.PhoneEntity;
import testdemo.emptyNumber.utils.PhoneStatusEnum;
import testdemo.emptyNumber.utils.PhoneUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author liuhai
 * @date 2019/10/11 10:21
 */
public class Demo {


    private final String CHECKACCOUNT = "S8564407";

    private final String CHECKPWD = "pwd3229745";

    private final String CHECKURL = "https://kh_bd.253.com/feign/apiMobileTest/findByMobilesNew";


    /**
     * 模拟userWeb查询数据
     *
     * @throws IOException
     */
    @Test
    void test2() throws IOException {
        JSONObject object = new JSONObject();
        String time = System.currentTimeMillis() + "";
        object.put("key", SecureUtil.md5("miaodi" + time));
        object.put("time", time);
        object.put("mobiles", "17681874926,17681874925");
        String result = Request.Post("http://localhost:8093/es/queryMobileStatus").body(new StringEntity(object.toString(), "utf-8"))
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("utf-8"));
        System.out.println(JSON.parseObject(result));
        //返回结果示例
        //[{"17681874926":{"city":"杭州","gmtCreate":1570870428459,"gmtModify":1570870428459,"id":"17681874926","operatorType":"联通176卡","phoneStatus":"1","province":"浙江","source":"1","tableName":"PHONE_176_MONITOR"}},{"17681874925":{"city":"杭州","gmtCreate":1570870428621,"gmtModify":1570870428621,"id":"17681874925","operatorType":"联通176卡","phoneStatus":"4","province":"浙江","source":"1","tableName":"PHONE_176_MONITOR"}}]
    }

    /**
     * 模拟通道推数据
     *
     * @throws Exception
     */
    @Test
    void test3() throws Exception {
        JSONObject resultObject = new JSONObject();
        String time = System.currentTimeMillis() + "";
        resultObject.put("time", time);
        //加密校验
        resultObject.put("key", SecureUtil.md5("miaodi" + time));

        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("phone", "17681874926");
        object.put("status", "DEVEPL");
        object.put("operatorType", "联通");
        object.put("province", "江苏");
        object.put("city", "杭州");
        jsonArray.add(object);
        object = new JSONObject();
        object.put("phone", "17681874925");
        object.put("status", "M:SA");
        object.put("operatorType", "移动");
        object.put("province", "江苏");
        object.put("city", "杭州");
        jsonArray.add(object);
        resultObject.put("data", jsonArray);
        ArrayList<NameValuePair> arrayList = new ArrayList<>();
        arrayList.add(new BasicNameValuePair("data", resultObject.toString()));
        String resultStr = Request.Post("http://localhost:8089/phone/es/synchronizePhone").body(new UrlEncodedFormEntity(arrayList, "UTF-8")).socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("UTF-8"));
        System.out.println(resultStr);
    }

    /**
     * 创蓝接口获取手机号的状态
     */
    @Test
    void test4() {
        Map<String, String> params = new HashMap<>(3);
        params.put("apiName", CHECKACCOUNT);
        params.put("password", CHECKPWD);
        params.put("mobiles", "17681874926");
        try {
            String responseStr = doPost(CHECKURL, params);
            System.out.println(responseStr);
        } catch (Exception e) {
        }
    }

    /**
     * 获取创蓝那边的空号检测的余额条数
     */
    @Test
    void test7() {
        Map<String, String> params = new HashMap<>(3);
        params.put("apiName", CHECKACCOUNT);
        params.put("password", CHECKPWD);
        try {
            String responseStr = doPost("https://kh_bd.253.com/user/getUserBalance", params);
            System.out.println(responseStr);
        } catch (Exception e) {
        }
    }

    /**
     * 获取随机数
     */
    @Test
    void test5() {
        List<String> lista = new ArrayList<String>(1);
        lista.add("111");
        lista.add("222");
        lista.add("333");
        System.out.println(lista);
    }


    @Test
    void test6() throws Exception {
        String fileName = "C:\\Users\\Administrator\\Desktop\\testPhone\\10.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String phone;
        //空号数目
        int emptyCount = 0;
        //实号数目
        int userCount = 0;
        //不存在的号码数目  包含了停机号码
        int noExistCount = 0;
        //沉默号号码数
        int silenceCount = 0;
        //风险号号码数
        int riskCount = 0;
        //错误号码号码数
        int errorCount = 0;
        //重复号码号码数
        int repeatCount = 0;
        //查询重复号码的集合
        Map<String, String> map = new HashMap<>(1);
        //待检测号码列表
        Map<String, String> pendingMap = new HashMap<>(1);
        //空号
        StringBuilder emptyPhone = new StringBuilder();
        //实号
        StringBuilder userPhone = new StringBuilder();
        //不存在的号码
        StringBuilder noExistPhone = new StringBuilder();
        //沉默号
        StringBuilder silencePhone = new StringBuilder();
        //风险号
        StringBuilder riskPhone = new StringBuilder();
        //错误号码
        StringBuilder errorPhone = new StringBuilder();
        //重复号码
        StringBuilder repeatPhone = new StringBuilder();
        long start = System.currentTimeMillis();
        while ((phone = reader.readLine()) != null) {
            //1.正常，2.错误，3.重复号码
            boolean vasPhone = PhoneUtil.isPhoneAdvanced(phone);
            if (!vasPhone) {
                //错误号码
                errorPhone.append(phone).append("\r\n");
                errorCount++;
            } else if (map.containsKey(phone)) {
                //重复号码
                repeatPhone.append(phone).append("\r\n");
                repeatCount++;
            } else {
                map.put(phone, phone);
                pendingMap.put(phone, phone);
                if (pendingMap.size() >= 100000) {
                    System.out.println("处理100000条数据需要的时间" + (System.currentTimeMillis() - start));
                    //  StringUtils.join(pendingMap.keySet().toArray()   将map的key转换为逗号分隔的字符串
                    String result = getNumberStatus(pendingMap);
                    List<PhoneEntity> resultList = JSON.parseArray(result, PhoneEntity.class);
                    for (PhoneEntity phoneEntity : resultList) {
                        if (ToolUtil.isNotEmpty(phoneEntity)) {
                            //空号和停机都属于空号
                            if (StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.EMPTY_PHONE.getCode())
                                    || StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.DOWNTIME_PHONE.getCode())) {
                                emptyPhone.append(phoneEntity.getId()).append("\r\n");
                                emptyCount++;
                            } else if (StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.USER_PHONE.getCode())) {
                                userPhone.append(phoneEntity.getId()).append("\r\n");
                                userCount++;
                            } else if (StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.NOEXIST_PHONE.getCode())) {
                                noExistPhone.append(phoneEntity.getId()).append("\r\n");
                                noExistCount++;
                            } else if (StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.SILENCE_PHONE.getCode())) {
                                silencePhone.append(phoneEntity.getId()).append("\r\n");
                                silenceCount++;
                            } else if (StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.RISK_PHONE.getCode())) {
                                riskPhone.append(phoneEntity.getId()).append("\r\n");
                                riskCount++;
                            }
                        }
                    }
                    pendingMap = new HashMap<>(1);
                }
            }
        }
        if (pendingMap.size() > 0) {
            String result = getNumberStatus(pendingMap);
            List<PhoneEntity> resultList = JSON.parseArray(result, PhoneEntity.class);
            for (PhoneEntity phoneEntity : resultList) {
                //空号和停机都属于空号
                if (StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.EMPTY_PHONE.getCode())
                        || StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.DOWNTIME_PHONE.getCode())) {
                    emptyPhone.append(phoneEntity.getId()).append("\r\n");
                    emptyCount++;
                } else if (StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.USER_PHONE.getCode())) {
                    userPhone.append(phoneEntity.getId()).append("\r\n");
                    userCount++;
                } else if (StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.NOEXIST_PHONE.getCode())) {
                    noExistPhone.append(phoneEntity.getId()).append("\r\n");
                    noExistCount++;
                } else if (StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.SILENCE_PHONE.getCode())) {
                    silencePhone.append(phoneEntity.getId()).append("\r\n");
                    silenceCount++;
                } else if (StringUtils.equals(phoneEntity.getPhoneStatus(), PhoneStatusEnum.RISK_PHONE.getCode())) {
                    riskPhone.append(phoneEntity.getId()).append("\r\n");
                    riskCount++;
                }
            }
        }
        String dayPath = DateUtil.format(new Date(), "yyyyMMdd");
        String emptyPhonePath = "G:/application/phone-monitor-file-temp/emptyPhone/" + dayPath + "." + "txt";
        String userPhonePath = "G:/application/phone-monitor-file-temp/userPhone/" + dayPath + "." + "txt";
        String noExistPhonePath = "G:/application/phone-monitor-file-temp/noExistPhone/" + dayPath + "." + "txt";
        String silencePhonePath = "G:/application/phone-monitor-file-temp/silencePhone/" + dayPath + "." + "txt";
        String riskPhonePath = "G:/application/phone-monitor-file-temp/riskPhone/" + dayPath + "." + "txt";
        String errorPhonePath = "G:/application/phone-monitor-file-temp/errorPhone/" + dayPath + "." + "txt";
        String repeatPhonePath = "G:/application/phone-monitor-file-temp/repeatPhone/" + dayPath + "." + "txt";
        FileToBase64.savePhoneFile(emptyPhone.toString(), emptyPhonePath, "UTF-8");
        FileToBase64.savePhoneFile(userPhone.toString(), userPhonePath, "UTF-8");
        FileToBase64.savePhoneFile(noExistPhone.toString(), noExistPhonePath, "UTF-8");
        FileToBase64.savePhoneFile(silencePhone.toString(), silencePhonePath, "UTF-8");
        FileToBase64.savePhoneFile(riskPhone.toString(), riskPhonePath, "UTF-8");
        FileToBase64.savePhoneFile(errorPhone.toString(), errorPhonePath, "UTF-8");
        FileToBase64.savePhoneFile(repeatPhone.toString(), repeatPhonePath, "UTF-8");
        JSONObject object = new JSONObject();
        object.put("emptyCount", emptyCount);
        object.put("userCount", userCount);
        object.put("noExistCount", noExistCount);
        object.put("silenceCount", silenceCount);
        object.put("riskCount", riskCount);
        object.put("errorCount", errorCount);
        object.put("repeatCount", repeatCount);
        object.put("emptyPhonePath", emptyPhonePath);
        object.put("userPhonePath", userPhonePath);
        object.put("noExistPhonePath", noExistPhonePath);
        object.put("silencePhonePath", silencePhonePath);
        object.put("riskPhonePath", riskPhonePath);
        object.put("errorPhonePath", errorPhonePath);
        object.put("repeatPhonePath", repeatPhonePath);
        System.out.println("生成完成！耗时" + (System.currentTimeMillis() - start) + "详细信息是：" + object.toJSONString());

    }

    /**
     * 生成号码文件
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            writePhoneNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writePhoneNumber() throws IOException {
        File file = new File("E:\\1.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file, true);

        Long startPhoneNumber = 18698000000L;
        for (int i = 0; i < 2000000; i++) {
            startPhoneNumber++;
            writer.write(startPhoneNumber.toString() + "\r\n");
        }

        writer.close();
        System.out.println("文件写入完毕！！！");
    }


    public static String doPost(String url, Map<String, String> params) throws Exception {
        String result = null;
        List paramList = new ArrayList();
        for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String value = String.valueOf(params.get(name));
            paramList.add(new BasicNameValuePair(name, value));
        }
        result = Request.Post(url).body(new UrlEncodedFormEntity(paramList, "utf-8"))
                .socketTimeout(15000).connectTimeout(30000).execute().returnContent().asString(Charset.forName("utf-8"));
        return result;
    }


    /**
     * 获取电话号码的状态数据
     *
     * @return
     */
    private String getNumberStatus(Map numbers) {
        JSONObject object = new JSONObject();
        String time = System.currentTimeMillis() + "";
        object.put("key", SecureUtil.md5("miaodi" + time));
        object.put("time", time);
        object.put("mobiles", JSON.toJSONString(numbers));
        String result = null;
        try {
            result = Request.Post("http://localhost:8089/phone/es/queryStatus").body(new StringEntity(object.toString(), "utf-8"))
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
