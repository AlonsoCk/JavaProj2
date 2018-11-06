package TCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 * @Title: TCP.TcpSever
 * @ProjectName JavaProj
 * @Description:熟悉流程
 * 创建服务器
 * 1.指定端口 使用ServerSocket创建服务器
 * 2.阻塞式等待连接 accept
 * 3.操作 处理输入输出流
 * 4.释放资源
 * @date 2018/11/4下午 3:14
 */
public class TcpSever {
    public static void main(String[] args) throws IOException {
//         * 1.指定端口 使用ServerSocket创建服务器
        ServerSocket ss = new ServerSocket(8888);
//         * 2.阻塞式等待连接 accept
        Socket client = ss.accept();
        System.out.println("一个客户端已建立连接.");
//          * 3.操作 处理输入输出流
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String data = dis.readUTF();
        System.out.println(data);
//         * 4.释放资源
        dis.close();
        client.close();
        ss.close();//服务器一般不关闭,除非做维护

    }
}
