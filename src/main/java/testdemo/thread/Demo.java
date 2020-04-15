package testdemo.thread;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhai
 * @date 2019/9/19 16:02
 */
@EnableScheduling
public class Demo {

    public static void main(String[] args) {
        try {
            List<Account> list = new ArrayList<Account>();

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\myTxt.txt"), "utf-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] arr = line.split("\\{");
                if (arr.length < 3) {
                    System.out.println(arr);
                }
                Account account = new Account((arr[0].substring(0, arr[0].lastIndexOf("."))), arr[1].substring(0, arr[1].lastIndexOf("}")), arr[2].substring(0, arr[2].lastIndexOf("}")));
                list.add(account);
            }
            System.out.println(list);

            String excelFileName = "F:\\myExcel.xls";
            WritableWorkbook wwk = null;
            wwk = Workbook.createWorkbook(new File(excelFileName));
            WritableSheet ws = wwk.createSheet("Test1", 0);
            Label label1 = new Label(0, 0, "id");
            Label label2 = new Label(1, 0, "word");
            Label label3 = new Label(2, 0, "desc");
            // 将定义好的单元格添加到工作表中
            ws.addCell(label1);
            ws.addCell(label2);
            ws.addCell(label3);
            for (int i = 0; i < list.size(); i++) {
                Account p = list.get(i);
                Label id = new Label(0, (i + 1), p.getNum());
                Label label5 = new Label(1, (i + 1), p.getName());
                Label label6 = new Label(2, (i + 1), p.getValue());
                ws.addCell(id);
                ws.addCell(label5);
                ws.addCell(label6);
            }
            wwk.write();
            wwk.close();
            System.out.println("完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void test1() throws IOException {




    }

    static class Account {

        private String num;
        private String name = "";
        private String value = "";

        public Account(String num, String name, String value) {
            this.num = num;
            this.name = name;
            this.value = value;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @Test
    void tets2() {
        String data = "v.临时调派，借调｝";

        System.out.println(data.substring(0, data.lastIndexOf("}")));
    }

}
