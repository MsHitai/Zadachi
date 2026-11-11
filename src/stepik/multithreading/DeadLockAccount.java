package stepik.multithreading;

/**
 * Демонстрация deadlock: два потока захватывают два монитора в противоположном порядке и ждут друг друга вечно.
 * Исправление — единый порядок захвата блокировок, см. {@link #transferSafely(boolean, int)}.
 */
public class DeadLockAccount {

    private int amount1;
    private int amount2;

    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    public DeadLockAccount(int amount1, int amount2) {
        this.amount1 = amount1;
        this.amount2 = amount2;
    }

    public void transferFrom1To2(int amount) {
        synchronized (monitor1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (amount <= amount1) {
                System.out.println("amount <= amount1");
                synchronized (monitor2) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    amount1 -= amount;
                    amount2 += amount;
                }
            } else {
                System.out.println("There is not enough money");
            }
        }
    }

    public void transferFrom2To1(int amount) {
        synchronized (monitor2) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (amount <= amount2) {
                System.out.println("amount <= amount2");
                synchronized (monitor1) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    amount2 -= amount;
                    amount1 += amount;
                }
            } else {
                System.out.println("There is not enough money");
            }
        }
    }

    /**
     * Как избежать deadlock: все потоки берут мониторы в одном и том же порядке (сначала monitor1, потом monitor2).
     * Тогда циклического ожидания не возникает. Методы выше специально берут их в разном порядке, чтобы показать
     * взаимную блокировку.
     */
    public void transferSafely(boolean from1To2, int amount) {
        synchronized (monitor1) {
            synchronized (monitor2) {
                if (from1To2 && amount <= amount1) {
                    amount1 -= amount;
                    amount2 += amount;
                } else if (!from1To2 && amount <= amount2) {
                    amount2 -= amount;
                    amount1 += amount;
                } else {
                    System.out.println("There is not enough money");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLockAccount account = new DeadLockAccount(1000, 1000);

        new Thread(() -> {
            account.transferFrom1To2(300);
        }).start();

        new Thread(() -> {
            account.transferFrom2To1(500);
        }).start();
    }
}
