package testdemo.Abstract;

/**
 * @author liuhai
 * @date 2020/3/20 17:05
 */
public class Demo4 extends Demo3 {
    @Override
    public void test1() {
        System.out.println("1111111111");
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo3();
        demo1.test1();
    }
}
