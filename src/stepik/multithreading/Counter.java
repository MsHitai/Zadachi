package stepik.multithreading;

public class Counter {

    private final Object monitor = new Object();
    private final Object monitor2 = new Object();
    private int value;
    private int value2;

    public int getValue() {
        return value;
    }

    public void increment() {
        synchronized (monitor2) {
            value++;
        }
    }

    public void decrement() { // public synchronized void равнозначно synchronized(this)
        synchronized (monitor2) {
            value--;
        }
    }

    public void increment2() {
        synchronized(monitor) {
            value2++;
        }
    }

    public void decrement2() {
        synchronized(monitor) {
            value2--;
        }
    }

    public int getValue2() {
        return value2;
    }
}
