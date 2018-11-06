import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Administrator
 * @Title: UdpSever
 * @ProjectName JavaProj
 * @Description:在线聊天接收端 1.使用DatagramSocket 指定端口 创建接收端
 * 2.准备容器 封装成 DatagramPacket包裹
 * 3.阻塞式接收包裹 receive(DatagramPacket p)
 * 4.分析数据
 * byte[] getdata()    byte[] getlength()
 * 5.释放资源
 * @date 2018/11/3上午 10:50
 */
public class UdpTalkSever {
    public static void main(String[] args) throws Exception {

        // * 1.使用DatagramSocket 指定端口 创建接收端
        System.out.println("接收端正在启动中...");

        DatagramSocket sever = new DatagramSocket(9999);
        // * 2.准备容器 封装成 DatagramPacket包裹
        while (true) {
            byte[] container = new byte[1024 * 60];
            DatagramPacket dp = new DatagramPacket(container, 0, container.length);
        //* 3.阻塞式接收包裹 receive(DatagramPacket p)
            sever.receive(dp);
        //* 4.分析数据
        // * byte[] getdata()    byte[] getlength()
            byte[] datas = dp.getData();
            int len = dp.getLength();//datas.len 也可以获得长度,这里是为了使用该方法
            String data = new String(datas, 0, len);
            System.out.println(data);
            if (data.equals("bye")) {
                break;
            }
        }
        // * 5.释放资源
        sever.close();
    }
}
