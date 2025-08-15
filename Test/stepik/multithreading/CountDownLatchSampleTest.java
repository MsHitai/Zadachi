package stepik.multithreading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CountDownLatchSampleTest {

    private CountDownLatchSample downLatchSample;
    private CountDownLatch count;
    private int test = 10;
    private ExecutorService executorService;

    @BeforeEach
    void setUp() {
        String monitor = "test";
        count = new CountDownLatch(test);
        downLatchSample = new CountDownLatchSample(monitor);
        executorService = Executors.newFixedThreadPool(test);
    }

    @Test
    public void testConcurrentBankTransactions() throws InterruptedException {
        AtomicInteger accountBalance = new AtomicInteger(1000);
        int numberOfTransactions = 5;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfTransactions);
        CountDownLatch latch = new CountDownLatch(numberOfTransactions);

        for (int i = 0; i < numberOfTransactions; i++) {
            executor.submit(() -> {
                try {
                    int currentBalance;
                    int newBalance;
                    do {
                        currentBalance = accountBalance.get();
                        newBalance = currentBalance - 100;
                    } while (!accountBalance.compareAndSet(currentBalance, newBalance));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        assertEquals(500, accountBalance.get(), "Balance after 5 concurrent withdrawals of 100 should be 500");
    }

    @Test
    public void getAndIncrementTest() throws InterruptedException {
        AtomicInteger actual = new AtomicInteger(0);

        for (int i = 0; i < test; i++) {
            executorService.execute(() -> {
                try {
                    actual.getAndSet(downLatchSample.getAndIncrement());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    count.countDown();
                }
            });
        }

        count.await();

        executorService.shutdown();

        assertEquals(test, actual.get());
        assertEquals(0, count.getCount());
    }
}