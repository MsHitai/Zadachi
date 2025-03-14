package ya.algorithm_course.first_sprint.final_test;

/*https://contest.yandex.ru/contest/22450/run-report/134924862/*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastTyping {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int k = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        String allButtons = sb.toString();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < allButtons.length(); i++) {
            if (allButtons.charAt(i) == '.') {
                continue;
            }
            numbers.add(Integer.parseInt(String.valueOf(allButtons.charAt(i))));
        }

        System.out.println(getMaxPoints(numbers, k));
    }

    private static int getMaxPoints(List<Integer> numbers, int k) {
        int count = 0;
        Map<Integer, Integer> numberCount = new HashMap<>();
        for (Integer number : numbers) {
            numberCount.put(number, numberCount.getOrDefault(number, 0) + 1);
        }
        for (Integer counts : numberCount.values()) {
            if (counts <= k * 2) {
                count++;
            }
        }
        return count;
    }
}
