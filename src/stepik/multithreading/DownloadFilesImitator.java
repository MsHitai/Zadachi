package stepik.multithreading;

import java.util.concurrent.CyclicBarrier;

public class DownloadFilesImitator {

    public static void workWithFileSystem() {
        String nameThread = Thread.currentThread().getName();
        System.out.println(nameThread + " began work with file");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nameThread + " finished.");
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                long millis = (long) (Math.random() * 5000 + 1000);
                String name = Thread.currentThread().getName();
                System.out.println(name + ": Data is being prepared");
                try {
                    Thread.sleep(millis);
                    System.out.println(name + ": Data is ready");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(name + ": Continue work");
            }).start();



        /*ExecutorService executorService = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                String nameThread = Thread.currentThread().getName();
                System.out.println(nameThread + " started working");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    semaphore.acquire();
                    DownloadFilesImitator.workWithFileSystem();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
                System.out.println(nameThread + " finished.");
            });
        }

        executorService.shutdown();*/
        }
    }
}
