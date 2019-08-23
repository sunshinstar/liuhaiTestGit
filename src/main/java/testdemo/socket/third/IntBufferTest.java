package testdemo.socket.third;

import org.junit.jupiter.api.Test;

import java.nio.IntBuffer;

/**
 * @author liuhai
 * @date 2019/8/14 17:27
 */
public class IntBufferTest {

    @Test
    void test1() {
        IntBuffer buffer = IntBuffer.allocate(11);
        buffer.put(11);
        buffer.put(5);
        buffer.put(32);
        System.out.println("未调用flip复位方法前的buffer：" + buffer);
        buffer.flip();
        System.out.println("调用flip复位方法后的buffer：" + buffer);
        System.out.println("buffer容量为：" + buffer.capacity());
        System.out.println("buffer限制为：" + buffer.limit());
        System.out.println("获取下标为1的元素：" + buffer.get(1));
        System.out.println("调用get(index)方法后的buffer：" + buffer);
        buffer.put(1, 4);
        System.out.println("调用put(index, value)方法后的buffer：" + buffer);
        for (int i = 0; i < buffer.limit(); i++) {
            //调用get方法会使缓冲区的位置(position)向后递增一位
            System.out.print(buffer.get() + "\t");
        }
        System.out.println("\nbuffer对象遍历之后buffer为：" + buffer);
    }

    @Test
    void test2() {

        int[] arr = new int[]{1, 2, 3};
        IntBuffer buffer = IntBuffer.wrap(arr);
        System.out.println("wrap(arr)方法：" + buffer);
        for (int i = 0; i < buffer.limit(); i++) {
            //调用get方法会使缓冲区的位置(position)向后递增一位
            System.out.print(buffer.get() + "\t");
        }
        System.out.println("\nbuffer对象遍历之后buffer为：" + buffer);
        buffer = IntBuffer.wrap(arr, 0, 2);
        System.out.println("wrap(arr, 0, 2)：" + buffer);
        for (int i = 0; i < buffer.limit(); i++) {
            //调用get方法会使缓冲区的位置(position)向后递增一位
            System.out.print(buffer.get() + "\t");
        }
        System.out.println("\nbuffer对象遍历之后buffer为：" + buffer);
    }

    @Test
    void test3(){
        IntBuffer buffer = IntBuffer.allocate(10);
        int[] arr = new int[]{1, 2, 3};
        buffer.put(arr);
        System.out.println("调用put(arr)方法后的buffer：" + buffer);
        IntBuffer buffer1 = buffer.duplicate();
        System.out.println("buffer1：" + buffer1);




    }


}
