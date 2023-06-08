package stepik.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicAndVolatile {

    private AtomicInteger value = new AtomicInteger();

    public void inc() {
        value.getAndIncrement();
    }

    public void dec() {
        value.getAndDecrement();
    }

    public int getValue() {
        return value.intValue();
    }

    public static void main(String[] args) {
        AtomicAndVolatile counter = new AtomicAndVolatile();

        int barrier = 1000;

        Thread thread1 = new Thread( () -> {

            for (int i = 0; i < barrier; i++) {
                counter.inc();
            }
        });

        Thread thread2 = new Thread( () -> {
            for (int i = 0; i < barrier; i++) {
                counter.dec();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter.getValue());
    }
}
