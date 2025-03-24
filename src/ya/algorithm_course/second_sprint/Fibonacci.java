package ya.algorithm_course.second_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Fibonacci {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            System.out.println(fib(n));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static int fib(int n) {
        if (n <= 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
