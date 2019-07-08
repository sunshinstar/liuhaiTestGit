package rabbitMq;

/**
 * @author liuhai
 * @date 2019/7/4 8:02
 */
public class ConfirmProducer {

    public static void main(String[] args) {
        String s1 = new String("111");
        String s2 = new String("111");
        String s3 = "111";
        System.out.println(s2.intern()==s3);
    }

}
