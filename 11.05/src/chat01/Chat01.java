package chat01;/*
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
 * @Description:聊天室v0.1
 * 实现单客户端发送消息后返回(还实现了循环返回)
 * @date 2018/11/5下午 9:55
 */
public class Chat01 {
    public static void main(String[] args) throws IOException {
//         * 1.指定端口 使用ServerSocket创建服务器
        System.out.println("-----Server-----");
        ServerSocket ss = new ServerSocket(8888);
//         * 2.阻塞式等待连接 accept
        Socket client = ss.accept();
        System.out.println("一个客户端已建立连接.");
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        boolean isRunning = true;
        while (isRunning) {
        String data = dis.readUTF();
        dos.writeUTF(data);
        dos.flush();
        }
        dos.close();
        dis.close();
        ss.close();
    }
}
