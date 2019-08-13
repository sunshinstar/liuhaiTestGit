package testdemo.boom;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * @author liuhai
 * @date 2019/8/9 11:02
 */
public class QQboom1 {
    public static void main(String[] args) throws AWTException, IOException {
        String sentence = "从前有座山,山里有座庙,庙里有个老和尚和小和尚,和尚对小和尚说：";// 定义要发送的话
        Robot robot = new Robot();// 创建Robot对象
        String QQ="3005852091";//这里设置你要发送的QQ号，需要已经添加好友
        String url="http://wpa.qq.com/msgrd?v=3&uin="+QQ+"&site=qq&menu=yes";//设置调用聊天框url
        String cmd = "explorer \"" +url+"\"";//通过cmd命令使用默认浏览器访问url
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            proc.waitFor();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        robot.delay(5000);// 延迟五秒，主要是为了预留出打开窗口的时间，括号内的单位为毫秒
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        String[] authors = sentence.split("[,]");// 字符串根据,分割
        for (int j = 0; j < 20; j++) {//循环次数
            for (int i = 0; i < authors.length; i++) {
                String sentencet = authors[i];
                Transferable tText = new StringSelection(sentencet);
                clip.setContents(tText, null);
                // for (int j = 1; j <= 1; j++) {
                // 以下两行按下了ctrl+v，完成粘贴功能
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);

                robot.keyRelease(KeyEvent.VK_CONTROL);// 释放ctrl按键，像ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放，不然会出问题。crtl如果按住没有释放，在按其他字母按键是，敲出来的回事ctrl的快捷键。
                robot.delay(500);// 延迟一秒再发送，不然会一次性全发布出去，因为电脑的处理速度很快，每次粘贴发送的速度几乎是一瞬间，所以给人的感觉就是一次性发送了全部。这个时间可以自己改，想几秒发送一条都可以
                robot.keyPress(KeyEvent.VK_ENTER);// 回车
                // }
            }
        }
    }

}
