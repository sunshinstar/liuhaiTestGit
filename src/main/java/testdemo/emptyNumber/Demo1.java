package testdemo.emptyNumber;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Test;
import testdemo.emptyNumber.utils.BigFileReader;
import testdemo.emptyNumber.utils.DateUtil;
import testdemo.emptyNumber.utils.Phone;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 测试大文件读取
 *
 * @author liuhai
 * @date 2019/10/15 15:38
 */
public class Demo1 {

    ExecutorService service = Executors.newFixedThreadPool(10);


    ExecutorService insertEsService = Executors.newFixedThreadPool(10);



    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong(0);
        String smallFilePath = "F:\\20191021.txt";
        BigFileReader.Builder builder = new BigFileReader.Builder(smallFilePath, line ->
                System.out.println(String.format("total record: %s,line is: %s", counter.incrementAndGet(), line))
        );
        BigFileReader bigFileReader = builder
                .threadPoolSize(20)
                .charset(StandardCharsets.UTF_8)
                .bufferSize(1024).build();
        bigFileReader.start();
    }


    /**
     * 常规读法
     *
     * @throws Exception
     */
    @Test
    void test1() throws Exception {
        long start = System.currentTimeMillis();
        String fileName = "F:\\20191021.txt";

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String number;
        int i = 0;
        Random random = new Random();
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone();
        while ((number = reader.readLine()) != null) {
            String[] numbers = number.split(",");
            phone.setId(numbers[3]);
            String tableName = "表名";
            phone.setId(number);
            phone.setGmtModify(System.currentTimeMillis());
            phone.setSource("接口");
            phone.setOperatorType("未知");
            phone.setGmtCreate(System.currentTimeMillis());
            phone.setProvince("未知");
            phone.setCity("未知");
            phone.setTableName(tableName);
            phone.setPhoneStatus(random.nextInt(5) + "");
            phones.add(phone);

            if (i % 2000000 == 0) {
                System.out.println(phones.size());
                phones = new ArrayList<>();
            }
            i++;
        }
        System.out.println("phones" + i);
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 获取文件的行数
     *
     * @throws IOException
     */
    @Test
    void test2() throws IOException {
        String fileName = "F:\\20191021-copy-106-txt\\1.txt";
        System.out.println(getFileLineNumber(new File(fileName)));

    }

    /**
     * 切割文件，  将文件切割成小文件尽心处理数据
     * @throws IOException
     */
    @Test
    void test3() throws IOException {
        long start = System.currentTimeMillis();
        isJudgeFileSize("F:\\250万.txt", "txt");
        System.out.println("切分文件耗时" + (System.currentTimeMillis() - start));
    }


    @Test
    void test4() {
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00"));
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59"));
        System.out.println(getTodayZeroPointTimestamps());
        //获取当天的最后一秒钟的时间戳
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        Date endOfDate = calendar2.getTime();
        System.out.println(endOfDate.getTime());

        System.out.println(DateUtil.getEndOfDay(new Date()).getTime());

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        int lastMonthMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 00, 00, 00);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01");
        String gtime2 = sdf2.format(c.getTime());
        Date date = DateUtil.parseDate(gtime2);
        long monthTime = DateUtil.parse(DateUtil.getDay(date), "yyyy-MM-dd").getTime();
        System.out.println(monthTime);


    }

    @Test
    void test5() throws IOException {
        Map<String, Integer> map = new HashMap<>();
        StringBuffer msg = new StringBuffer();
        msg.append("发布/过期 消息中心消息：").append("启用").append(map.get("start"))
                .append("条,停用").append(map.get("stop"));
        System.out.println(msg.toString());
    }

    class MyTread extends Thread {
        @Override
        public void run(){
            for (int i=0; i<20; i++) {
                System.out.println("run" + i);
            }
        }
    }

    @Test
    void test6() throws InterruptedException {
        CountDownLatch await = new CountDownLatch(10);

        // 依次创建并启动处于等待状态的5个MyRunnable线程
        for (int i = 0; i < 5; ++i) {
            new Thread(new MyRunnable( await)).start();
        }
        await.await();
        System.out.println("Bingo!");

    }

    @Test
    void test7(){
        String data = "{\"callId\":123456,\"caller\":\"112233\",\"callee\":\"445566\"}";
        JSONObject jsonObject = JSON.parseObject(data);
        System.out.println(jsonObject.getOrDefault("callId",""));
    }

    public class MyRunnable implements Runnable {

        private final CountDownLatch await;

        public MyRunnable( CountDownLatch await) {
            this.await = await;
        }

        public void run() {
            try {
                System.out.println("处于等待的线程开始自己预期工作......");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                await.countDown();
            }
        }
    }




    class    User{
        /**
         * 转发限制的列表
         */
        private List<String> forwardList;

        public List<String> getForwardList() {
            return forwardList;
        }

        public void setForwardList(List<String> forwardList) {
            this.forwardList = forwardList;
        }
    }

    /**
     * 获取当日零点的时间戳
     *
     * @return
     */
    public Long getTodayZeroPointTimestamps() {
        Long currentTimestamps = System.currentTimeMillis();
        Long oneDayTimestamps = Long.valueOf(60 * 60 * 24 * 1000);
        return currentTimestamps - (currentTimestamps + 60 * 60 * 8 * 1000) % oneDayTimestamps;
    }

    /**
     * 判断文件大小 同时进行拆分
     *
     * @param path
     */
    private String isJudgeFileSize(String path, String type) throws IOException {

        //文件大小
        Integer size = (new FileInputStream(new File(path)).available()) / 1024 / 1024;
        String ip = "106";
            String dir = path.substring(0, path.indexOf("."));
            dir += "-copy-" + ip + "-" + type;
            File myPath = new File(dir);
            if (!myPath.exists()) {
                myPath.mkdirs();
                System.out.println("创建文件夹路径为：" + dir);
            }
            //在此目录 拆分文件 myPath
            String line = null;
            BufferedWriter output = null;
            int i = 0;
            int j = 0;
            int k = 0;
            String[] numberArray;
            Map<String, String> phoneMap = new HashMap<>(10000);
            LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");
            while (it.hasNext()) {
                line = it.nextLine();
                // 排除第一行，第一行是说明
                Pattern pattern = compile(".*\\d+.*");
                Matcher matcher = pattern.matcher(line);
                boolean matches = matcher.matches();
                if (!matches) {
                    continue;
                }
                numberArray = line.split(",");
                if ("DELIVRD".equals(numberArray[9]) || "0".equals(numberArray[9])) {
                    if (phoneMap.containsKey(numberArray[3])) {
                        continue;
                    }
                    phoneMap.put(numberArray[3], numberArray[9]);
                    k++;
                    if (i == 0) {
                        j++;
                        //每个块建立一个输出
                        output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(myPath + "/" + j + ".txt")), "utf-8"));
                        i = k;
                        phoneMap = new HashMap<>(160000);
                    }
                    line = numberArray[3];
                    output.append(line);
                    output.newLine();
                    if (k == 160000) {
                        i = 0;
                        k = 0;
                        output.flush();
                        output.close();
                    }
                }
            }
            phoneMap.clear();
            output.flush();
            output.close();
            System.out.println(path + "文件重构拆分成功！");
            return dir;
    }


    private int getFileLineNumber(File file) throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader(file));
        lnr.skip(Long.MAX_VALUE);
        int lineNo = lnr.getLineNumber() + 1;
        lnr.close();
        return lineNo;
    }


}
