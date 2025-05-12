package ya.algorithm_course.first_sprint_pointers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class EvenUneven {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        boolean result;
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int a = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());
        result = isEven(a, b, c) || isUneven(a, b, c);
        if (result) {
            System.out.println("WIN");
        } else {
            System.out.println("FAIL");
        }
    }

    private static boolean isUneven(int a, int b, int c) {
        return a % 2 != 0 && b % 2 != 0 && c % 2 != 0;
    }

    private static boolean isEven(int a, int b, int c) {
        return a % 2 == 0 && b % 2 == 0 && c % 2 == 0;
    }
}
