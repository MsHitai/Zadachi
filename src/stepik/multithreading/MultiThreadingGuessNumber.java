package stepik.multithreading;

import java.util.Random;
import java.util.Scanner;

public class MultiThreadingGuessNumber {
    private static boolean isGuessed = false;

    public static void main(String[] args) {

        Random rnd = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Think of a number from 0 to 100");
        int input = scanner.nextInt();

        Thread thread = new Thread( () -> {
            while (!isGuessed) {
                int random = rnd.nextInt(101);
                if (random == input) {
                    System.out.println("Your number is " + input);
                    isGuessed = true;
                }
            }
        });
        thread.start();

        Thread thread2 = new Thread( () -> {
            for (int i = 1; i < 10000; i++) {
                if (isGuessed) {
                    break;
                }
                System.out.println(i + " seconds...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } );
        thread2.start();
    }
}