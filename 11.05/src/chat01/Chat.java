package chat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 * @Title: TCP.TcpSever
 * @ProjectName JavaProj
 * @Description:模拟登陆(双向)
 * 创建服务器
 * 1.指定端口 使用ServerSocket创建服务器
 * 2.阻塞式等待连接 accept
 * 3.操作 处理输入输出流
 * 4.释放资源
 * @date 2018/11/5下午 9:54
 */
public class Chat {
    public static void main(String[] args) throws IOException {
//         * 1.指定端口 使用ServerSocket创建服务器
        System.out.println("-----Server-----");

        ServerSocket ss = new ServerSocket(8888);
//         * 2.阻塞式等待连接 accept
        Socket client = ss.accept();
        System.out.println("一个客户端已建立连接.");
//          * 3.操作 处理输入输出流
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String UserName = null;
        String UserPwd = null;

        String data = dis.readUTF();
        String[] dataArray = data.split("&");
        for (String info : dataArray) {
            String[] userinfo = info.split("=");
            if (userinfo[0].equals("Uname")) {
                UserName = userinfo[1];
                System.out.println("你的用户名为:"+UserName);
            } else if (userinfo[0].equals("Upwd")) {
                UserPwd = userinfo[1];
                System.out.println("你输入的密码为:"+UserPwd);
            }
        }
        //双向通信之输出
        if (UserName.equals("shuaikang") && UserPwd.equals("1234")) {
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF("恭喜您,登陆成功!");
            dos.flush();
        } else {
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF("您的输入有误!");
            dos.flush();
        }
//         * 4.释放资源
        dis.close();
        client.close();
        ss.close();//服务器一般不关闭,除非做维护

    }
}
