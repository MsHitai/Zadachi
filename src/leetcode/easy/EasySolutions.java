package leetcode.easy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("unused")
public class EasySolutions {

    public static void main(String[] args) throws FileNotFoundException {
        int[] digits_10 = new int[]{9};
        System.out.println(Arrays.toString(plusOneShort(digits_10)));

        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        System.out.println(findCombinations(n, k));
    }

    private static int findCombinationsWithRepetitions(int n, int k) {
        int numerator = k + n - 1;
        int denumerator = factorial(n - 1) * factorial(k);
        return factorial(numerator) / denumerator;
    }

    private static int findCombinations(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else return n * factorial(n - 1);
    }

    public static int theMaximumAchievableX(int num, int t) {
        return num + (2 * t);
    }

    public static int scoreOfString(String s) {
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            result = result + Math.abs(s.charAt(i - 1) - s.charAt(i));
        }
        return result;
    }

    public static int[] plusOneShort(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static int[] plusOne(int[] digits) {
        increase(digits);
        return Arrays.stream(digits)
                .mapToObj(String::valueOf)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void increase(int[] digits) {
        int result = digits[digits.length - 1];
        if (result != 9 || digits.length == 1) {
            int digit = digits[digits.length - 1];
            digits[digits.length - 1] = digit + 1;
            return;
        }
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9 && i != 0) {
                digits[i] = 0;
                continue;
            }
            if (digits[i] != 9) {
                digits[i] = digits[i] + 1;
                break;
            }
            if (i == 0) {
                int value = digits[i] == 9 ? 10 : digits[i] + 1;
                digits[i] = value;
            }
        }
    }

    public static int[] getConcatenation(int[] nums) {
        int[] result = new int[nums.length * 2];
        result = Arrays.copyOf(nums, result.length);
        int j = nums.length;
        for (int num : nums) {
            result[j] = num;
            j++;
        }
        return result;
    }
}
