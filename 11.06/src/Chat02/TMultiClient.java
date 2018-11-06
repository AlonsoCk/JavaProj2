package Chat02;/*
 *definition:
 *
 */

import java.io.*;
import java.net.Socket;

/**
 * @author Administrator
 * @Title: Client01
 * @ProjectName JavaProj
 * @Description:
 * @date 2018/11/6上午 8:50
 */
public class TMultiClient {
    public static void main(String[] args) throws IOException {
        // *1. 指定端口 使用Socket创建客户端
        System.out.println("-----Client-----");
        Socket client = new Socket("localhost",8888);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());
        boolean isRunning = true;
        while (isRunning) {
            String msg =br.readLine();
            // *3. 操作 处理输入输出流

            dos.writeUTF(msg);
            dos.flush();

            String data = dis.readUTF();
            System.out.println(data);

        }
        // *4. 释放资源
        dos.close();
        dis.close();
        client.close();

    }
}
