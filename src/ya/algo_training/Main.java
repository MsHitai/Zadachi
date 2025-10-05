package ya.algo_training;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            int[] nums = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(tokenizer.nextToken());
            }
            System.out.println(findMaxHappiness(nums));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int findMaxHappiness(int[] nums) {
        int replaceOnce = 1;
        int sumVasya = 0;
        int sumMasha = 0;

        int max = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }

        for (int i = 0; i < nums.length - 1; i+=2) {
            if (nums[i] < nums[i + 1] && nums[i + 1] == max && replaceOnce > 0) {
                sumVasya += nums[i + 1];
                sumMasha += nums[i];
                replaceOnce--;
            } else {
                sumVasya += nums[i];
                sumMasha += nums[i + 1];
            }
        }

        if (nums.length % 2 != 0) {
            sumVasya += nums[nums.length - 1];
        }

        return sumVasya - sumMasha;
    }
}
