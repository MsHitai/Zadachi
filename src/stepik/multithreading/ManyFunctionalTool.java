package stepik.multithreading;

public class ManyFunctionalTool {

    private final Object printMonitor = new Object();
    private final Object scanMonitor = new Object();

    public void print(int quantityPages) {
        synchronized (printMonitor) {
            try {
                for (int i = 0; i < quantityPages; i++) {
                    System.out.println("Отпечатано " + (i + 1) + " стр.");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void scan(int quantityPages) {
        synchronized (scanMonitor) {
            try {
                for (int i = 0; i < quantityPages; i++) {
                    System.out.println("Отсканировано " + (i + 1) + " стр.");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        ManyFunctionalTool mft = new ManyFunctionalTool();

        new Thread(() -> {
            mft.print(3);
        }).start();

        new Thread(() -> {
            mft.scan(3);
        }).start();
    }
}
