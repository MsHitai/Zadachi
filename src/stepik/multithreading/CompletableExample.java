package stepik.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Main Thread: " + Thread.currentThread().getName());

        CompletableFuture<Void> future = CompletableFuture
                .runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Run Async Future Thread: " + Thread.currentThread().getName());
                })
                .thenAcceptAsync(runAsync -> System.out.println(" run Async is done"));
        future.get();

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        return "Supply Async Future Thread: " + Thread.currentThread().getName();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .thenApplyAsync(supply -> supply + " supply async is done");
        System.out.println(future2.get());

        CompletableFuture<Double> positive = CompletableFuture.supplyAsync(() -> {
            System.out.println("Retrieving positive number... ");
            for (int i = 0; i < 1000; i++) {
                if (i == 888) {
                    System.out.println("you won " + i + " points!");
                }
            }
            return 100.0;
        });

        CompletableFuture<Double> negative = CompletableFuture.supplyAsync(() -> {
            System.out.println("Retrieving negative number... ");
            for (int i = -1000; i < 0; i++) {
                if (i == -788) {
                    System.out.println("oops someone took " + Math.abs(i) + " of your points!");
                }
            }
            return -80.0;
        });

        CompletableFuture<Double> combined = positive.thenCombineAsync(negative, (positiveValue, negativeValue) ->
                positiveValue - negativeValue);

        System.out.println("pos - neg = " + combined.get());

        List<String> list = new ArrayList<>();
        CompletableFuture<String> exceptionFuture = CompletableFuture.supplyAsync(() -> {
            if (list.isEmpty()) {
                throw new IllegalArgumentException("The experiment list is empty. Who would've thought?!");
            }
            return "the work finished successfully with a list that has something inside";
        }).exceptionally(exception -> {
            System.out.println("Exception: " + exception.getMessage());
            return "Exception occurred!";
        });
        System.out.println("Result: " + exceptionFuture.get());
    }
}
