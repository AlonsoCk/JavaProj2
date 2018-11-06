import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

/**
 * @author Administrator
 * @Title: UdpSever
 * @ProjectName JavaProj
 * @Description:引用类型:接收端
 * 1.使用DatagramSocket 指定端口 创建接收端
 * 2.准备容器 封装成 DatagramPacket包裹
 * 3.阻塞式接收包裹 receive(DatagramPacket p)
 * 4.分析数据
 * byte[] getdata()    byte[] getlength()
 * 5.释放资源
 * @date 2018/11/4上午 10:50
 */
public class UdpObjectSever {
    public static void main(String[] args) throws Exception {

// * 1.使用DatagramSocket 指定端口 创建接收端
        System.out.println("接收端正在启动中...");

        DatagramSocket sever = new DatagramSocket(9999);
    // * 2.准备容器 封装成 DatagramPacket包裹
    byte[] container = new byte[1024 * 60];
    DatagramPacket dp = new DatagramPacket(container, 0, container.length);
//* 3.阻塞式接收包裹 receive(DatagramPacket p)
    sever.receive(dp);
//* 4.分析数据
// * byte[] getdata()    byte[] getlength()
        byte[] datas = dp.getData();
        int len = dp.getLength();//datas.len 也可以获得长度,这里是为了使用该方法
        //读取 -->反序列化
        ObjectInputStream ois =new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
        //顺序与写出一致
        String msg = ois.readUTF();
        int age = ois.readInt();
        boolean flag = ois.readBoolean();
        char ch = ois.readChar();
        System.out.println(flag);
        //对象的数据还原
        Object str = ois.readObject();
        Object date = ois.readObject();
        Object employee = ois.readObject();

        if(str instanceof String) {
            String strObj = (String) str;
            System.out.println(strObj);
        }
        if(date instanceof Date) {
            Date dateObj = (Date) date;
            System.out.println(dateObj);
        }
        if(employee instanceof Employee) {
            Employee empObj = (Employee) employee;
            System.out.println(empObj.getName()+"-->"+empObj.getSalary());
        }
// * 5.释放资源
    }
}
