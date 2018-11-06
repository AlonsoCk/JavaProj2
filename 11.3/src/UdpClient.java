import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author Administrator
 * @Title: UdpClient
 * @ProjectName JavaProj
 * @Description:发送端
 *  1.使用DatagramSocket 指定端口 创建发送端
 *  2.准备数据 封装成 DatagramPacket包裹 需要指定目的地
 *  3.发送包裹 send(DatagramPacket p)
 *  4.释放资源
 * @date 2018/11/3上午 10:50
 */
public class UdpClient {
    public static void main(String[] args) throws Exception {
//     *  1.使用DatagramSocket 指定端口 创建发送端
        System.out.println("发送端正在启动中...");
        DatagramSocket client = new DatagramSocket(8888);
//     *  2.准备数据 封装成 DatagramPacket包裹 需要指定目的地
        String data = "学习Java,走向成功!";
        byte[] datas = data.getBytes();
        DatagramPacket dp = new DatagramPacket(datas,0,datas.length,
                new InetSocketAddress("localhost",9999));
//     *  3.发送包裹 send(DatagramPacket p)
        client.send(dp);
//     *  4.释放资源
        client.close();
    }
}
