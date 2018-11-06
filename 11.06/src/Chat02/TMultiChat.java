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
 * @Description:聊天室v0.2 使用多线程实现多客户端发送消息后返回
 * 问题:1.代码不易维护;2.客户端读写没有分开,必须先写后读
 * @date 2018/11/6上午 8:50
 */
public class TMultiChat {
    public static void main(String[] args) throws IOException {
//         * 1.指定端口 使用ServerSocket创建服务器
        ServerSocket ss = new ServerSocket(8888);
        System.out.println("-----Server-----");
        while (true) {
//         * 2.阻塞式等待连接 accept
            new Thread(() -> {
                DataInputStream dis = null;
                DataOutputStream dos = null;
                Socket client = null;
                try {
                    client = ss.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("一个客户端已建立连接.");
                try {
                    dis = new DataInputStream(client.getInputStream());
                    dos = new DataOutputStream(client.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                boolean isRunning = true;
                while (isRunning) {
                    String data = null;
                    try {
                        data = dis.readUTF();
                        dos.writeUTF(data);
                        dos.flush();
                    } catch (IOException e) {
//                        e.printStackTrace();
                        isRunning =false;
                    }
                }
                try {
                    if (dos != null) {
                        dos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (dis != null) {
                        dis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (ss != null) {
                        ss.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
