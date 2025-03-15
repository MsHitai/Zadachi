package ya.algorithm_course.first_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Factorization {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int number = Integer.parseInt(reader.readLine());
        System.out.println(factorization(number));
    }

    private static String factorization(int number) {
        StringBuilder sb = new StringBuilder();
        int j = 2;
        while (number > 1) {
            if (number % j == 0) {
                sb.append(j).append(" ");
                number = number / j;
            } else {
                j = getDividor(number, j);
            }
        }

        return sb.toString().trim();
    }

    private static int getDividor(int number, int current) {
        if (current % 2 == 0) {
            return current + 1;
        }
        if (current < Math.sqrt(number)) {
            return current + 2;
        }
        return number;
    }
}
