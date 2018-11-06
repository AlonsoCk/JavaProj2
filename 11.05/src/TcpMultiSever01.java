import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 * @Title: TCP.TcpSever
 * @ProjectName JavaProj
 * @Description:模拟登陆(双向) 创建服务器
 * 1.指定端口 使用ServerSocket创建服务器
 * 2.阻塞式等待连接 accept
 * 3.操作 处理输入输出流
 * 4.释放资源
 * @date 2018/11/4上午 9:35
 */
public class TcpMultiSever01 {
    public static void main(String[] args) throws IOException {
//         * 1.指定端口 使用ServerSocket创建服务器
        System.out.println("-----Server-----");
        ServerSocket ss = new ServerSocket(8888);
        Boolean isRunning = true;
//         * 2.阻塞式等待连接 accept
        while (isRunning) {
            Socket client = ss.accept();
            System.out.println("一个客户端已建立连接.");
            new Thread(new Channel(client)).start();
        }
        ss.close();//服务器一般不关闭,除非做维护
    }
    public static class Channel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket client;

        // 构造器 输入输出流
        public Channel(Socket client) {
            try {
                this.client = client;
                this.dis = new DataInputStream(client.getInputStream());
                this.dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                release();/*有异常擦才关闭,每异常关闭了后面怎么读?*/
            }
        }

        //接收
        private String receive() {
            String data = "";
            try {
                data = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        //释放资源
        private void release() {
            try {
                if (dos !=null) {
                dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dis !=null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client !=null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //发送
        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String UserName = null;
            String UserPwd = null;
            String[] dataArray = receive().split("&");
            for (String info : dataArray) {
                String[] userinfo = info.split("=");
                if (userinfo[0].equals("Uname")) {
                    UserName = userinfo[1];
                    System.out.println("你的用户名为:" + UserName);
                } else if (userinfo[0].equals("Upwd")) {
                    UserPwd = userinfo[1];
                    System.out.println("你输入的密码为:" + UserPwd);
                }
            }
            //双向通信之输出
            if (UserName.equals("shuaikang") && UserPwd.equals("1234")) {
                send("登陆成功,欢迎回来!");
            } else {
                send("您的输入有误!");
            }
//         * 4.释放资源
            release();
        }
    }

}
