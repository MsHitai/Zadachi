package stepik.multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class BlockQueue {

    private final Object monitor = new Object();
    private final Queue<Runnable> queue = new LinkedList<>();

    public void add(Runnable task) {
        synchronized (monitor) {
            queue.add(task);
            monitor.notify(); // пробуждаем поток
        }
    }

    public Runnable take() {
        synchronized (monitor) {
            while (queue.isEmpty()) {
                try {
                    monitor.wait(); // пока очередь пуста, поток спит и не тратит ресурсы компьютера
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return queue.poll();
        }
    }

    public static void main(String[] args) {
        BlockQueue blockQueue = new BlockQueue();

        new Thread( () -> {
            int i = 0;
            while (true) {
                System.out.println("Count: " + i);
                i++;
                Runnable task = blockQueue.take();
                if(task != null) {
                    new Thread(task).start();
                }
            }
        } ).start();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            blockQueue.add(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("----" + index);
                }
            });
        }

    }
}
