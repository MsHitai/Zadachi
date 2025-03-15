package ya.algorithm_course.first_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BinaryConverter {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int number = Integer.parseInt(reader.readLine());
        System.out.println(convertToBinaryNumber(number));
    }

    private static String convertToBinaryNumber(int number) {
        if (number == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (number != 0) {
            sb.append(number % 2);
            number = number / 2;
        }
        sb.reverse();
        return sb.toString();
    }
}
