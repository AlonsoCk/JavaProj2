/**
 * @author Administrator
 * @Title: COTest02
 * @ProjectName JavaProj
 * @Description:协作模型:生产者消费者实现方式-管程法 借助缓冲区实现+加入并发
 * @date 2018/10/31上午 11:27+3:42
 */
public class COTest01 {
    public static void main(String[] args) {
        SynContainer SC = new SynContainer();
        new Thread(new Consumer(SC)).start();
        new Thread(new Producer(SC)).start();
    }
}
//生产者
class Producer extends Thread {
    SynContainer sc;

    public Producer(SynContainer sc) {
        this.sc = sc;
    }
    @Override
    public void run() {
        //生产
        for (int i = 0; i < 100; i++) {
            System.out.println("生产-->第"+(i+1)+"馒头");
            sc.push(new Steambun(i));
        }
    }
}
//消费者
class Consumer extends Thread{
    SynContainer sc;

    public Consumer(SynContainer sc) {
        this.sc = sc;
    }
    @Override
    public void run() {
        //消费
        for (int i = 0; i < 100; i++) {
            System.out.println("消费-->第"+(sc.pop().id+1)+"馒头");
        }
    }
}
//容器-缓冲区
class SynContainer{
    Steambun[] buns = new Steambun[10];//定义容器大小
    int count = 0;//计数器

    //存储生产
    public synchronized void push(Steambun bun) {
        //何时生产 容器容量未满时生产
        //不能生产
        if (count == buns.length) {
            try {
                this.wait();//不能生产,等待消费者通知时生产
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //可以生产
        buns[count] = bun;
        count++;
        this.notifyAll();//容器中有资源了,通知对方生产
    }
    //获取消费
    public synchronized Steambun pop(){
        //何时可以消费 容器中有资源时
        //不能消费
        if (count == 0) {
            try {
                this.wait();//不能消费,阻塞,等待生产者通知消费
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        this.notifyAll();//容器存在空间了,通知对方生产
        Steambun bun = buns[count];
        return bun;
    }
}
//商品-馒头
class Steambun{
    int id;
    public Steambun(int id) {
        this.id = id;
    }
}

