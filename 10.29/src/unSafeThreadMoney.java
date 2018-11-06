/*
* definition:不安全线程取钱
* */


public class unSafeThreadMoney {
    public static void main(String[] args) {
        Account account = new Account(100, "结婚钱");
        Drawing me = new Drawing(account, 70, "苦逼的你");
        Drawing wife = new Drawing(account, 80, "happy的她");
        me.start();
        wife.start();
    }

}

class Account {
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread {
    Account account;//取钱的账户
    int drawMoney;
    int totalGetMoney;

    public Drawing(Account account, int drawMoney, String name) {
        super(name);
        this.account = account;
        this.drawMoney = drawMoney;
        account.money -= drawMoney;
    }
    @Override
    public void run() {
//        super.money
        System.out.println("账户余额为："+account.money+"万");
        totalGetMoney+=drawMoney;
        System.out.println("一共取到了："+totalGetMoney+"万");
    }
}