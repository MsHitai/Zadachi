package ya.algorithm_course.first_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Palindrome {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String line = reader.readLine().toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i)) || Character.isAlphabetic(line.charAt(i))) {
                sb.append(line.charAt(i));
            }
        }
        String copy = sb.toString();
        sb.reverse();
        boolean palindrome = sb.toString().equals(copy);
        if (palindrome) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}
