package LambdasStreams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuhai
 * @date 2019/5/24 14:35
 */
public class DemoTest {
    List<Person> javaProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
            add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
        }
    };
    List<Person> phpProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
            add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
            add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
            add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
            add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
            add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
            add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
            add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
            add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
            add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
        }
    };

    @Test
    void test1() {
        List<Integer> list1 = new Vector<>();
        List<Integer> list2 = new Vector<>();
        List<Integer> list3 = new Vector<>();
        Lock lock = new ReentrantLock();
        List<Integer> lists = new Vector<>();
        for (int j = 0; j < 10000; j++) {
            lists.add(j);
        }

        lists.forEach(list1::add);
        lists.parallelStream().forEach(list->{
            list2.add(list);
        });
        lists.forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } finally {
                lock.unlock();
            }
        });
        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("加锁并行执行的大小：" + list3.size());


    }



    @Test
    public void Test2() {
        List<Integer> i = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println("stream.forEach打印结果如下");
        i.stream().forEach(System.out::print);
        // 固定结果 1234567
        System.out.println("parallelStream.forEach打印结果如下");
        i.parallelStream().forEach(System.out::print);
        //每次的结果都不同
        System.out.println("parallelStream.forEachOrdered打印结果如下");
        i.parallelStream().forEachOrdered(System.out::print);
        //结果同stream.forEach

    }
}
