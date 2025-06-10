package ya.algorithm_course.seventh_sprint_dynamic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Actions {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            int[] actions = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < actions.length; i++) {
                actions[i] = Integer.parseInt(tokenizer.nextToken());
            }
            System.out.println(findMaxProfit(actions));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int findMaxProfit(int[] actions) {
        int greedyMax = 0;

        for (int i = 0; i < actions.length - 1; i++) {
            if (actions[i] < actions[i + 1]) {
                greedyMax += actions[i + 1] - actions[i];
            }
        }

        return greedyMax;
    }
}
