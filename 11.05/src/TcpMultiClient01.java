import java.io.*;
import java.net.Socket;

/**
 * @author Administrator
 * @Title: TCP.TcpClient
 * @ProjectName JavaProj
 * @Description:模拟登陆 多客户端登陆
 * 1.指定端口 使用Socket创建客户端(需要指定服务器的地址和端口.)
 * 2.操作 处理输入输出流
 * 3.释放资源
 * @date 2018/11/5上午 8:59
 */
public class TcpMultiClient01 {
    public static void main(String[] args) throws IOException {
        // *1. 指定端口 使用Socket创建客户端
        System.out.println("-----Client-----");

        //建立连接
        Socket client = new Socket("localhost", 8888);
        // *3. 操作 处理输入输出流
        new Send(client).send();
        new Receive(client).receive();

        // *4. 释放资源
        client.close();
    }


    static class Send {
        private DataOutputStream dos;
        private Socket client;
        private BufferedReader br;
        private String msg;
        public Send(Socket client) {
            this.client = client;
            try {
                this.dos = new DataOutputStream(client.getOutputStream());
                this.msg =init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String init() {
            this.br= new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.print("请输入用户名:");
                String Uname = br.readLine();
                System.out.print("请输入密码:");
                String Upwd = br.readLine();
                return "Uname="+Uname+"&"+"Upwd="+Upwd;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
        public void send() {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Receive {
        private DataInputStream dis ;
        private Socket client;
        public Receive(Socket client) {
            this.client = client;
            try {
                this.dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void receive() {
            try {
                String data = dis.readUTF();
                System.out.println(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
