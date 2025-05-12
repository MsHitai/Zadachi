package ya.algorithm_course.first_sprint_pointers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DividedByFour {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int number = Integer.parseInt(reader.readLine());
        if (isDividedByFour(number)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    private static boolean isDividedByFour(int number) {
        int n;
        for (int i = 0; i < number; i++) {
            n = (int) Math.pow(4, i);
            if (n > number) {
                return false;
            } else if (n == number) {
                return true;
            }
        }
        return false;
    }

}
