package test;

/**
 * @author liuhai
 * @date 2019/7/1 10:10
 */
public class User {

    Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                '}';
    }

    public static void main(String[] args) {
        User user=new User();
        user.setAge(1);
        System.out.println(user.toString());

    }
}
