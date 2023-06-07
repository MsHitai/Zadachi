package stepik;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchSample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CountDownLatch countDownLatch = new CountDownLatch(10); // waits for the number of threads in params

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> { // передали в executorService реализацию интерфейса Runnable
                    System.out.println("Start - " + index);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Finish - " + index);
                    countDownLatch.countDown();
            });
        }
        executorService.shutdown(); // с этого момента в него уже нельзя передавать новые задачи

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Все потоки завершили работу.");

        ExecutorService executor1 = Executors.newSingleThreadExecutor(); // как fixed, но у него 1 поток создается

        ExecutorService executor = Executors.newCachedThreadPool(); // создает потоки по мере необходимости,
                                                                // пул не уменьшается, может привезти к утечке ресурсов
    }
}
