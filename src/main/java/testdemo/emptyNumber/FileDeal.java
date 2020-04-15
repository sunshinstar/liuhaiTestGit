package testdemo.emptyNumber;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author liuhai
 * @date 2020/1/7 14:17
 */
public class FileDeal {

    @Test
    void test1(){
        String fileName = "C:\\Users\\Administrator\\Desktop\\2019-12-07_2020-01-07_300W.txt";
        try {
            String phone;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((phone = reader.readLine()) != null) {
                if( phone.startsWith("16")||phone.startsWith("19")){

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
