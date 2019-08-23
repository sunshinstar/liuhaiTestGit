package testdemo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhai
 * @date 2019/8/20 14:38
 */
public class TestDemo {

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student();
        student.setAge(111);
        student.setName("222");
        studentList.add(student);

        Person person = new Person();

        person.setList(studentList);

        List list = person.getList();
        list.clear();
        System.out.println(student);
        System.out.println(person.getList());
    }
}

class Person {

    private String name = "";
    private int age = 0;
    private List list;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

 class Student {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}