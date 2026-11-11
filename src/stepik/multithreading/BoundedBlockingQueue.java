package stepik.multithreading;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Ограниченная блокирующая очередь (LeetCode 1188 "Design Bounded Blocking Queue") — классика собеседований
 * по многопоточности, основа паттерна producer–consumer.
 * <p>
 * enqueue ждёт, пока появится место, dequeue — пока появится элемент. Два условия на одной блокировке, чтобы будить
 * только нужную сторону. Ожидание всегда в цикле while: поток может проснуться ложно (spurious wakeup)
 * или элемент успеет забрать другой поток.
 */
public class BoundedBlockingQueue<T> {

    private final Queue<T> items = new ArrayDeque<>();
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        try {
            while (items.size() == capacity) {
                notFull.await();
            }
            items.add(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (items.isEmpty()) {
                notEmpty.await();
            }
            T item = items.poll();
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return items.size();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(2);
        int total = 10;

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < total; i++) {
                    queue.enqueue(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        long[] sum = new long[1];
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < total; i++) {
                    sum[0] += queue.dequeue();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Consumed sum = " + sum[0] + ", expected 45");
    }
}
