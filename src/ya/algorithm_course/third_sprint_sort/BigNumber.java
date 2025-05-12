package ya.algorithm_course.third_sprint_sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BigNumber {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int qty = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            String[] numbers = new String[qty];
            for (int i = 0; i < qty; i++) {
                numbers[i] = tokenizer.nextToken();
            }
            BigNumber bigNumber = new BigNumber();
            List<String> list = Arrays.stream(numbers)
                    .sorted(bigNumber::compare).toList();
            StringBuilder sb = new StringBuilder();
            for (int i = list.size() - 1; i >= 0; i--) {
                sb.append(list.get(i));
            }
            System.out.println(sb);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private int compare(String first, String second) {
        if (first.equals(second)) {
            return 0;
        }
        int one = Integer.parseInt(String.valueOf(first.charAt(0)));
        int two = Integer.parseInt(String.valueOf(second.charAt(0)));
        if (one == two && (first.length() > 1 || second.length() > 1)) {
            one = Integer.parseInt(first + second);
            two = Integer.parseInt(second + first);
        }
        return Integer.compare(one, two);
    }
}
