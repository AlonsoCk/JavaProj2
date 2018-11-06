package Chat02;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Administrator
 * @Title: ChatUtils
 * @ProjectName JavaProj
 * @Description:为封装提供的工具类
 * @date 2018/11/6上午 10:21
 */
public class ChatUtils {
    /*释放资源*/
    public static void close(Closeable... targets) {
        for (Closeable target:targets) {
            if (target != null) {
                try {
                    target.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
