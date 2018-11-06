package Chat02;/*
 *definition:
 *
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 * @Title: Chat01
 * @ProjectName JavaProj
 * @Description:聊天室v0.2 使用多线程实现多客户端发送消息后返回+封装
 * @date 2018/11/6上午 8:50
 */
public class TMultiChat02 {
    public static void main(String[] args) throws IOException {
//         * 1.指定端口 使用ServerSocket创建服务器
        ServerSocket ss = new ServerSocket(8888);
        System.out.println("-----Server-----");
        while (true) {
            Socket  client =ss.accept();
            System.out.println("一个客户端建立了连接");
            new Thread(new Channel(client)).start();
        }
    }

    //一个客户端用一个Channel代表
    static class Channel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket client;
        boolean isRunning;
        public Channel(Socket client) {
            this.client = client;
            try {
                this.dis = new DataInputStream(client.getInputStream());
                this.dos = new DataOutputStream(client.getOutputStream());
                isRunning = true;
            } catch (IOException e) {
                System.out.println("------1------");
                e.printStackTrace();
                release();
            }
        }

        //接收消息
        private String recevie() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                System.out.println("------2------");
                release();
            }
            return msg;
        }
        //发送消息
        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                System.out.println("------3------");
                release();
            }
        }
        //释放资源
        private void release() {
            this.isRunning = false;
            ChatUtils.close(dos, dis, client);
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = recevie();
                if (!msg.equals("")) {
                    send(msg);
                }
            }
        }
    }
}

