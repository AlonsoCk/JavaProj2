/**
 * @author Administrator
 * @Title: deadLock
 * @ProjectName JavaProj
 * @Description:模拟化妆过程的死锁
 * @date 2018/10/31上午 9:43
 */
public class deadLock {
    public static void main(String[] args) {
        new Makeup(0, "石原里美").start();
        new Makeup(1, "木村佳乃").start();

    }

}

class Lipstick {

}
class Mirror{
}

class Makeup extends Thread {
    int choice;//选择
    String girl;//名字
    static Lipstick lipstick =new Lipstick();
    static Mirror mirror = new Mirror();

    public Makeup(int choice, String girl) {
        this.choice = choice;
        this.girl = girl;
    }

    public void run() {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.girl+"拿到口红啦!");
                try {
                    Thread.sleep(1000);//一秒后想要镜子
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (mirror) {
                    System.out.println(this.girl+"拿到镜子了,正在化妆");
                }
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.girl+"拿到镜子了");
                try {
                    Thread.sleep(2000);//2秒之后想要口红,故意设置时间差形成死锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lipstick) {
                    System.out.println(this.girl+"拿到口红了,正在化妆!");
                }
            }
        }
    }
}