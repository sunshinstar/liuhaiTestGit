package exportexcel;

import com.alibaba.excel.metadata.BaseRowModel;
import com.github.crab2died.annotation.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuhai
 * @date 2019/5/21 8:55
 */
@Data
@NoArgsConstructor   //生成一个无参构造方法
@AllArgsConstructor     //生成一个包含所有变量的构造方法
@Builder    //WriteModel.builder().name("刘海" + i).password("123456").age(i + 1).build();   这就是它的作用，返回一个实体
public class WriteModelExcel extends BaseRowModel {

    @ExcelField(title = "姓名",order = 1)
    private  String name;

    @ExcelField(title = "密码",order = 2)
    private String password;

    @ExcelField(title = "年龄",order = 3)
    private int age;

}


