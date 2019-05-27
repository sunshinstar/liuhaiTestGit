package LambdasStreams;

/**
 * @author liuhai
 * @date 2019/5/24 15:30
 */
public interface Defaulable {

    default String notQuired() {
        return "success";
    }

    static void  static1(){
        System.out.println("static 11111111");
    }




}
