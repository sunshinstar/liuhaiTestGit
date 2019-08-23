package testdemo.socket.second;

import org.junit.jupiter.api.Test;
import testdemo.socket.second.sercer.ServerHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuhai
 * @date 2019/8/14 16:18
 */
public class TestSocket {

    private static int port = 8378;

    private static int clientPort = 8378;

    private static String ip = "127.0.0.1";

    @Test
    void server(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务器端启动了！！！！");
            //进行阻塞
            Socket socket = serverSocket.accept();
            //启动一个线程来处理客户端的请求
            HandlerExecutorPool handlerExecutorPool = new HandlerExecutorPool(50,100);
            while (true){
                socket = serverSocket.accept();
                handlerExecutorPool.excute(new ServerHandler(socket));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            serverSocket = null;
        }
    }


    @Test
    void  client(){

        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        Socket socket = null;
        try {
            socket = new Socket(ip, clientPort);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter.println("客户端请求了服务器！");
            String response = bufferedReader.readLine();
            System.out.println("client:" + response);

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(printWriter!=null){
                try {
                    printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            socket = null;
        }

    }

}
