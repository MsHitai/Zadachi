package ya.algorithm_course.eighth_sprint_strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseString {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String input = reader.readLine();
            List<String> words = new java.util.ArrayList<>(Arrays.stream(input.split(" ")).toList());
            Collections.reverse(words);
            System.out.println(String.join(" ", words));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
