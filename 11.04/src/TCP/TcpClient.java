package TCP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Administrator
 * @Title: TCP.TcpClient
 * @ProjectName JavaProj
 * @Description:模拟登陆(单向)
 * 1.指定端口 使用Socket创建客户端(需要指定服务器的地址和端口.)
 * 2.操作 处理输入输出流
 * 3.释放资源
 * @date 2018/11/4下午 3:15
 */
public class TcpClient {
    public static void main(String[] args) throws IOException {
    // *1. 指定端口 使用Socket创建客户端
        Socket client = new Socket("localhost",8888);
    // *3. 操作 处理输入输出流
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("你好!");
        dos.flush();
    // *4. 释放资源
        client.close();
    }
}
