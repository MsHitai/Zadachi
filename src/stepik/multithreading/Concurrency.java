package stepik.multithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Concurrency {

    private static final int SIZE = 10_000_000;
    private static final int HALF = SIZE / 2;

    public static void main(String[] args) {

        float[] numbers1 = new float[SIZE];
        float[] numbers2 = new float[SIZE];

        startTimer();

        withoutConcurrency(numbers1);

        withConcurrency(numbers2);

        Collection<String> sample = new ArrayList<>();

    }

    private static void withoutConcurrency(float[] nums) {
        Arrays.fill(nums, 1f);

        long before = System.currentTimeMillis();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (float) (nums[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }

    private static void withConcurrency(float[] nums) {
        Arrays.fill(nums, 1f);
        float[] firstHalf = new float[HALF];
        float[] secondHalf = new float[HALF];

        long before = System.currentTimeMillis();

        System.arraycopy(nums, 0, firstHalf, 0, HALF);
        System.arraycopy(nums, HALF, secondHalf, 0, HALF);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < firstHalf.length; i++) {
                float f = (float) i;
                firstHalf[i] = (float) (firstHalf[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < firstHalf.length; i++) {
                float f = (float) i;
                secondHalf[i] = (float) (secondHalf[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(firstHalf, 0, nums, 0, HALF);
        System.arraycopy(secondHalf, 0, nums, HALF, HALF);

        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }

    private static void startTimer() {
        Thread timer = new Thread(() -> {
            int seconds = 0;
            try {
                while (true) {
                    System.out.println(seconds++);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timer.setDaemon(true);
        timer.start();
    }
}

/*System.out.println("Start");
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                System.out.print(i);
            }
        });
        thread.start();

        thread1.join(); // заставляет главный поток остановиться и ждать завершения потока у кот вызвали джоин

        for (int i = 0; i < 1000; i++) {
            if (i == 100) {
                thread.interrupt();
            }
            System.out.print("m");
        }
        System.out.println("\nFinish");*/
