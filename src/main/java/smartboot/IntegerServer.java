package smartboot;

import org.smartboot.socket.transport.AioQuickServer;

import java.io.IOException;

/**
 * @author o_0sky
 * @date 2019/4/24 11:41
 */
public class IntegerServer {

    public static void main(String[] args) throws IOException {
        AioQuickServer<Integer> server = new AioQuickServer<Integer>(8888, new IntegerProtocol(), new IntegerServerProcessor());
        server.start();
    }

}
