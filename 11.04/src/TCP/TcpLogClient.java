package TCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Administrator
 * @Title: TCP.TcpClient
 * @ProjectName JavaProj
 * @Description:创建客户端
 * 1.指定端口 使用Socket创建客户端(需要指定服务器的地址和端口.)
 * 2.操作 处理输入输出流
 * 3.释放资源
 * @date 2018/11/4下午 3:15
 */
public class TcpLogClient {
    public static void main(String[] args) throws IOException {
    // *1. 指定端口 使用Socket创建客户端
        System.out.println("-----Client-----");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名:");
        String Uname =br.readLine();
        System.out.println("请输入密码");
        String Upwd =br.readLine();
        Socket client = new Socket("localhost",8888);
    // *3. 操作 处理输入输出流
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("Uname="+Uname+"&"+"Upwd="+Upwd);
        dos.flush();
    // *4. 释放资源
        client.close();
    }
}
