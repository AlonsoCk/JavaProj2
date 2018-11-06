package TCP;

import java.io.*;
import java.net.Socket;

/**
 * @author Administrator
 * @Title: TCP.TcpClient
 * @ProjectName JavaProj
 * @Description:文件上传客户端
 * 1.指定端口 使用Socket创建客户端(需要指定服务器的地址和端口.)
 * 2.操作 处理输入输出流
 * 3.释放资源
 * @date 2018/11/4下午 3:15
 */
public class TcpFileClient {
    public static void main(String[] args) throws IOException {
    // *1. 指定端口 使用Socket创建客户端+服务端的地址和端口
        System.out.println("-----Client-----");
        Socket client = new Socket("localhost",8888);
    // *2. 操作 文件读入 上传
        InputStream is = new BufferedInputStream(new FileInputStream("E:\\JavaProj\\11.04\\src\\UDP\\lmei.jpg"));
        OutputStream os = new BufferedOutputStream(client.getOutputStream());
        byte[] flush = new byte[1024];
        int len = -1;
        while ((len = is.read(flush)) != -1) {//read :从输入流中读取一定数量的字节到字节数组中,返回读入字节的数量
            os.write(flush, 0, len);
        }
        os.flush();
        os.close();
        is.close();
    // *4. 释放资源
        client.close();
    }
}
