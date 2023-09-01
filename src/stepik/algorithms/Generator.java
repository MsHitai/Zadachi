package stepik.algorithms;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Generator {

    public static void main(String[] args) throws FileNotFoundException {
        new Generator().run();
    }

    private void run() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("input.txt");
        int n = 10_000;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            printWriter.print((char) ('a' + random.nextInt(26)));
        }
        printWriter.close();
    }
}
