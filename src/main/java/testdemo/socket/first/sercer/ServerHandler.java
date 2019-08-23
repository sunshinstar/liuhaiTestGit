package testdemo.socket.first.sercer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author liuhai
 * @date 2019/8/14 16:08
 */
public class ServerHandler implements Runnable {


    private Socket sockete;

    public ServerHandler(Socket sockete) {
        this.sockete = sockete;
    }

    @Override
    public void run() {

        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(sockete.getInputStream()));
            printWriter = new PrintWriter(sockete.getOutputStream(), true);
            while(true){
                String info = bufferedReader.readLine();
                if(info==null){
                    break;
                }
                System.out.println("客户端发送的消息" + info);
                printWriter.println("服务器端响应了客户端请求！！！！");
            }
        } catch (Exception e ) {
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
            if(sockete!=null){
                try {
                    sockete.close();
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            sockete = null;


        }


    }
}
