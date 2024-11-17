package leetcode.easy;

import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class EasySolutions {

    public static void main(String[] args) {
        int[] digits_123 = new int[]{1, 2, 3};
        int[] digits_4322 = new int[]{4, 3, 2, 1};
        int[] digits_10 = new int[]{9};
        int[] digits_9876543210 = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] digits_tooLong = new int[]{7, 2, 8, 5, 0, 9, 1, 2, 9, 5, 3, 6, 6, 7, 3, 2, 8, 4, 3, 7, 9, 5, 7, 7, 4, 7,
                4, 9, 4, 7, 0, 1, 1, 1, 7, 4, 0, 0, 6};
        int[] digits_4999 = new int[]{4, 9, 9, 9};
        int[] digits_999 = new int[]{9, 9, 9};
        System.out.println(Arrays.toString(plusOne(digits_999)));
        System.out.println(Arrays.toString(plusOne(digits_123)));
        System.out.println(Arrays.toString(plusOne(digits_4322)));
        System.out.println(Arrays.toString(plusOne(digits_10)));
        System.out.println(Arrays.toString(plusOne(digits_9876543210)));
        System.out.println(Arrays.toString(plusOne(digits_tooLong)));
        System.out.println(Arrays.toString(plusOne(digits_4999)));
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

    public int[] plusOneShort(int[] digits) {
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
        String numberValue = Arrays.stream(digits)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
        int[] result = new int[numberValue.length()];
        for (int i = 0; i < numberValue.length(); i++) {
            result[i] = Integer.parseInt(String.valueOf(numberValue.charAt(i)));
        }
        return result;
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
