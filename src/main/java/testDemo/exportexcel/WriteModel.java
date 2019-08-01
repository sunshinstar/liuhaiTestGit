package testDemo.exportexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
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
public class WriteModel  extends BaseRowModel {

    @ExcelProperty(value = "姓名" ,index=0)
    private  String name;

    @ExcelProperty(value = "密码" ,index = 1)
    private String password;

    @ExcelProperty(value = "年龄", index = 2)
    private int age;

}


