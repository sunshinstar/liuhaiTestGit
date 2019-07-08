package Callable;

/**
 * @author liuhai
 * @date 2019/6/19 8:17
 */
public class CommonCook {
    public static void main(String[] args) throws  Exception {

        Long startTime = System.currentTimeMillis();
        // 第一步 网购厨具
        OnlineShoping thread = new OnlineShoping();
        thread.start();
        thread.join();
        // 第二步 去超市购买食材
        // 模拟购买食材时间
        Thread.sleep(2000);
        Shicai shicai = new Shicai();
        System.out.println("第二步：食材到位");
        // 第三步 用厨具烹饪食材
        System.out.println("第三步：开始展现厨艺");
        cook(thread.chuju, shicai);

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    static  class OnlineShoping extends Thread{
        private Chuju chuju;
        @Override
        public void run() {
            System.out.println("第一步：下单");
            System.out.println("第一步：等待送货");
            try {
                // 模拟送货时间
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一步：快递送到");
            chuju = new Chuju();
        }
    }

    static void cook(Chuju chuju, Shicai shicai) {}

    static class Chuju {}

    static class Shicai {}

}
