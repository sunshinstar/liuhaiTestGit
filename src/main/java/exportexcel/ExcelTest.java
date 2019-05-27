package exportexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.github.crab2died.ExcelUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * @author liuhai
 * @date 2019/5/21 11:26
 */
public class ExcelTest {

    Calendar calendar = null;


    /**
     * 阿里巴巴的一个方法封装
     *
     * @throws Exception
     */
    @Test
    public void write() throws Exception {
        OutputStream out = new FileOutputStream("D:liuhai.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);
        Sheet sheet = new Sheet(1, 0, WriteModel.class);
        sheet.setSheetName("第一个案例");
        writer.write(creatModelList(), sheet);
        writer.finish();
        out.close();
    }

    /**
     * Excel4J的使用
     */
    @Test
    void test2() throws Exception {
        FileOutputStream outputStream = new FileOutputStream(new File("D:liuhai3.xlsx"));
        ExcelUtils.getInstance().exportObjects2Excel(creatModelList(), WriteModel.class, true, null, true, outputStream);
    }

    @Test
    void test3()   {


    }


    private List<WriteModel> creatModelList() {
        List<WriteModel> writeModels = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            WriteModel writeModel = WriteModel.builder().name("刘海" + i).password("123456").age(i + 1).build();
            writeModels.add(writeModel);
        }
        return writeModels;
    }

}
