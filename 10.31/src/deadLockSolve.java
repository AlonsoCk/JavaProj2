/**
 * @author Administrator
 * @Title: deadLock
 * @ProjectName JavaProj
 * @Description:解决化妆过程的死锁
 * @date 2018/10/31上午 9:43
 */
public class deadLockSolve {
    public static void main(String[] args) {
        new Makeup02(0, "石原里美").start();
        new Makeup02(1, "木村佳乃").start();

    }

}

class Lipstick02 {

}

class Mirror02 {
}

class Makeup02 extends Thread {
    int choice;//选择
    String girl;//名字
    static Lipstick02 lipstick = new Lipstick02();
    static Mirror02 mirror = new Mirror02();

    public Makeup02(int choice, String girl) {
        this.choice = choice;
        this.girl = girl;
    }

    public void run() {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.girl + "拿到口红啦!");
                try {
                    Thread.sleep(1000);//一秒后想要镜子
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                synchronized (mirror) {
//                    System.out.println(this.girl+"拿到镜子了,正在化妆");
            }
            synchronized (mirror) {
                System.out.println(this.girl + "拿到镜子了,正在化妆!");
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.girl + "拿到镜子了");
                try {
                    Thread.sleep(2000);//2秒之后想要口红,故意设置时间差形成死锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                synchronized (lipstick) {
//                    System.out.println(this.girl+"拿到口红了,正在化妆!");
            }
            synchronized (lipstick) {
                System.out.println(this.girl + "拿到口红了,正在化妆!");
            }
        }
    }
}