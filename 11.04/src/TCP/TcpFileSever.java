package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 * @Title: TCP.TcpSever
 * @ProjectName JavaProj
 * @Description:文件接收 存储
 * 创建服务器
 * 1.指定端口 使用ServerSocket创建服务器
 * 2.阻塞式等待连接 accept
 * 3.操作 处理输入输出流
 * 4.释放资源
 * @date 2018/11/4下午 3:14
 */
public class TcpFileSever {
    public static void main(String[] args) throws IOException {
//         * 1.指定端口 使用ServerSocket创建服务器
        System.out.println("-----Server-----");

        ServerSocket ss = new ServerSocket(8888);
//         * 2.阻塞式等待连接 accept
        Socket client = ss.accept();
        System.out.println("一个客户端已建立连接.");
//          * 3.操作 处理输入输出流
        InputStream is = new BufferedInputStream(client.getInputStream());
        OutputStream os = new BufferedOutputStream(new FileOutputStream("src\\TCP\\tcp.jpg"));
        byte[] flush = new byte[1024];
        int len = -1;
        while ((len = is.read(flush)) != -1) {//read :从输入流中读取一定数量的字节到字节数组中,返回读入字节的数量
            os.write(flush, 0, len);
        }
        os.flush();
        os.close();
        is.close();
//         * 4.释放资源

        client.close();
        ss.close();//服务器一般不关闭,除非做维护

    }
}
