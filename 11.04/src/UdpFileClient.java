import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author Administrator
 * @Title: UdpClient
 * @ProjectName JavaProj
 * @Description:文件:发送端
 *  1.使用DatagramSocket 指定端口 创建发送端
 *  2.将基本类型转成字节数组
 *  3.封装成 DatagramPacket包裹 需要指定目的地
 *  4.发送包裹 send(DatagramPacket p)
 *  5.释放资源
 * @date 2018/11/4上午 10:50
 */
public class UdpFileClient {
    public static void main(String[] args) throws Exception {
//     *  1.使用DatagramSocket 指定端口 创建发送端
        System.out.println("发送端正在启动中...");
        DatagramSocket client = new DatagramSocket(8888);
//     *  2.准备数据 一定要转成字节数组
        byte[] datas = IOUtils.fileToByteArray("E:\\JavaProj\\11.04\\src\\lmei.jpg");
//     *  3.封装成 DatagramPacket包裹 需要指定目的地
        DatagramPacket dp =new DatagramPacket(datas,0,datas.length,
                new InetSocketAddress("localhost",9999));

//     *  4.发送包裹 send(DatagramPacket p)
        client.send(dp);
//     *  5.释放资源
        client.close();
    }
}
