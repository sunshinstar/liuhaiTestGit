package api;

import api.common.DefaultThreadFactory;
import api.common.HttpUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuhai
 * @date 2020/4/30 8:40
 */
public class APIDevDemo {

    private static String accountSid = "02461e9800c05825138c616d39b744c5";
    private static String token = "33df60dba1b205210b794c5bf7ae2497";
    private static long to = 15100000001L;

    private ExecutorService executorService = new ThreadPoolExecutor(10,
            15,
            5 * 60,
            // idle timeout
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
            new DefaultThreadFactory("insertToAndLog-"));


    public static void main(String[] args) throws Exception {
        APIDevDemo am = new APIDevDemo();
        am.execute();
    }


    /**
     * 批量发送使用
     */
    public void execute() {
        String url = "http://192.168.11.1:8087/distributor/sendSMS";
        long startTime1 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1200000; i++) {
            sb.append(to++).append(",");
            if ((i + 1) % 1000 == 0) {
                String body = "accountSid=" + accountSid + "&to=" + (sb.subSequence(0, sb.lastIndexOf(","))) + "&templateid=" + 241484 + HttpUtil.createCommonParam(accountSid, token);
                executorService.execute(() -> {
                    String post = HttpUtil.post(url, body);
                    System.out.println("结果是" + post);
                });
                sb = new StringBuilder();
            }
        }
        System.out.println("=======================请求总共耗时:" + (System.currentTimeMillis() - startTime1));
    }


    /**
     * 发送单条使用
     *
     * @throws InterruptedException
     */
    public void executeSingle() throws InterruptedException {
        long startTime1 = System.currentTimeMillis();
        for (int i = 1; i <= 1; i++) {
            String url = "http://192.168.11.1/distributor/sendSMS";
            String body = "accountSid=" + accountSid + "&to=" + (to++) + "&templateid=" + 1432 + HttpUtil.createCommonParam(accountSid, token);
            String result = HttpUtil.post(url, body);
            System.out.println("result:" + System.lineSeparator() + result);
        }
        System.out.println("=======================请求总共耗时:" + (System.currentTimeMillis() - startTime1));
    }


    /**
     * 生成号码文件
     *
     * @throws IOException
     */
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


}
