package stepik.multithreading;

import java.util.concurrent.*;

public class CallableAndFuture {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        executorService.execute(() -> {
            try {
                while (true) {
                    System.out.print(".");
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // submit возвращает параметризированный объект типа Future

        Future<String> futureName = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000); // здесь можно не оборачивать в try / catch
                return "John";
            }
        });

        Future<Integer> futureAge = executorService.submit(() -> {

            Thread.sleep(5000); // здесь можно не оборачивать в try / catch
            return 27;

        });

        try {
            String name = futureName.get();
            int age = futureAge.get();
            System.out.println("\nName: " + name + " Age: " + age);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
