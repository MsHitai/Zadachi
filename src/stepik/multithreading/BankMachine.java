package stepik.multithreading;

public class BankMachine {

    private double money;

    public BankMachine(double money) {
        this.money = money;
    }

    public synchronized void drawMoney(String name, double amount) {
        System.out.println(name + " пришел в банкомат");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (money >= amount) {
            System.out.println(name + " вывел деньги");
            money -= amount;
            System.out.println("В банкомате осталось " + money + " рублей");
        } else {
            System.out.println("В банкомате закончились деньги");
        }
    }

    public static void main(String[] args) {
        BankMachine bankMachine = new BankMachine(50_000);

        Thread thread1 = new Thread(() -> bankMachine.drawMoney("Johnny", 10000));

        Thread thread2 = new Thread(() -> bankMachine.drawMoney("Max", 30000));

        Thread thread3 = new Thread(() -> bankMachine.drawMoney("Nick", 20000));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
