package testdemo.emptyNumber;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
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
        long start = System.currentTimeMillis();
        String fileName = "F:\\20191021.txt";
        System.out.println(getFileLineNumber(new File(fileName)));

    }

    @Test
    void test3() throws IOException {
        long start = System.currentTimeMillis();
        isJudgeFileSize("F:\\20191021.txt", "txt");
        System.out.println("切分文件耗时" + (System.currentTimeMillis() - start));
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
        if (size > 1024) {
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
                    phoneMap.put(numberArray[9], numberArray[9]);
                    k++;
                    if (i == 0) {
                        j++;
                        //每个块建立一个输出
                        output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(myPath + "/" + j + ".txt")), "utf-8"));
                        i = k;
                    }
                    line = numberArray[3];
                    output.append(line);
                    output.newLine();
                    if (k == 20000000) {
                        i = 0;
                        k = 0;
                        output.flush();
                        output.close();
                    }
                }
            }
            output.flush();
            output.close();
            System.out.println(path + "文件拆分成功！");
            return dir;
        }
        return null;

    }


    private int getFileLineNumber(File file) throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader(file));
        lnr.skip(Long.MAX_VALUE);
        int lineNo = lnr.getLineNumber() + 1;
        lnr.close();
        return lineNo;
    }


}
