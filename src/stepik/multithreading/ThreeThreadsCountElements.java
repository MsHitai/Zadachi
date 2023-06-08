package stepik.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreeThreadsCountElements {

    private static final int LIM = 1_000_000;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor(); // Executors.newFixedThreadPool(3);

        CountDownLatch countDownLatch = new CountDownLatch(3);

        Thread thread1 = new Thread(() -> {
            long sum = 0;
            for (int i = 0; i < LIM; i++) {
                if (i % 2 == 0) {
                    sum += i;
                }
            }

            System.out.println("Сумма всех четных чисел = " + sum);
            countDownLatch.countDown();
        });

        Thread thread2 = new Thread(() -> {
            long sum = 0;
            for (int i = 0; i < LIM; i++) {
                if (i % 7 == 0) {
                    sum += i;
                }
            }

            System.out.println("Сумма всех чисел, которые делятся на 7 без остатка = " + sum);
            countDownLatch.countDown();
        });

        List<Integer> randomNums = new ArrayList<>();
        Random random = new Random();

        Thread thread3 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                randomNums.add(random.nextInt(1000));
            }
            int quantity = 0;
            for (Integer randomNum : randomNums) {
                if (randomNum % 2 == 0) {
                    quantity++;
                }
            }
            System.out.println("Количество четных чисел в коллекции случайных цифр = " + quantity);
            countDownLatch.countDown();
        });

        long before = System.currentTimeMillis();

        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread3);

        executorService.shutdown();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long after = System.currentTimeMillis();

        System.out.println("Время выполнения: " + (after - before));
    }
}
