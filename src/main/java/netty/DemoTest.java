package netty;

import com.zx.sms.codec.cmpp.msg.CmppSubmitRequestMessage;
import org.junit.jupiter.api.Test;
import org.marre.sms.SmsAlphabet;
import org.marre.sms.SmsMsgClass;
import org.marre.sms.SmsTextMessage;

/**
 * @author liuhai
 * @date 2020/3/9 17:43
 */
public class DemoTest {

    @Test
    void test1(){
        CmppSubmitRequestMessage msg = CmppSubmitRequestMessage.create("17681874926", "10690021", "");
        msg.setMsgContent(new SmsTextMessage("你好，我是闪信！", SmsAlphabet.UCS2, SmsMsgClass.CLASS_0));  //class0是闪信

    }
}
