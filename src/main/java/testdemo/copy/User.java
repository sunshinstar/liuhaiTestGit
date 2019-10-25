package testdemo.copy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * @author liuhai
 * @date 2019/9/27 11:36
 */
public class User {

    private String name;
    private Address address;

    public User(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

class Address {
    private String city;
    private String country;

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}


class TestDemo {

    @Test
    void test1() {
        Address address = new Address("杭州", "中国");
        User user = new User("大山", address);
        // 调用构造函数时进行深拷贝
        User copyUser = new User(user.getName(), new Address(address.getCity(), address.getCountry()));

        // 修改源对象的值
        user.getAddress().setCity("深圳");

        System.out.println(user.getAddress().getCity());
        System.out.println(copyUser.getAddress().getCity());

        // 检查两个对象的值不同
        assertNotSame(user.getAddress().getCity(), copyUser.getAddress().getCity());

    }
}