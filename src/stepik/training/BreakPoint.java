package stepik.training;

import java.util.Scanner;

/**
 * <a href="https://stepik.org/lesson/1246037/step/5?unit=1259855">...</a>
 */
public class BreakPoint {

    public static void main(String[] args) {
        int[] n = readInput();
        boolean result1 = hasBreakPoint(n);
        boolean result2 = hasBreakPointTwoIndex(n);

        System.out.println("first method: " + result1);
        System.out.println("better variant: " + result2);
    }

    private static boolean hasBreakPointTwoIndex(int[] nums) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        int leftSum = nums[leftIndex];
        int rightSum = nums[rightIndex];

        while (leftIndex < rightIndex - 1) {
            if (leftSum <= rightSum) {
                leftSum += nums[++leftIndex];
            } else {
                rightSum += nums[--rightIndex];
            }
        }
        return leftSum == rightSum;
    }

    private static boolean hasBreakPoint(int[] numbers) {
        int halfSize;
        if (numbers.length % 2 == 0) {
            halfSize = numbers.length / 2;
        } else {
            halfSize = numbers.length / 2 + 1;
        }
        int left = getSum(halfSize, numbers, 0);
        int right = getSum(numbers.length, numbers, halfSize);

        if (left == right) {
            return true;
        }
        halfSize++;
        left = getSum(halfSize, numbers, 0);
        right = getSum(numbers.length, numbers, halfSize);
        return left == right;
    }

    private static int getSum(int limit, int[] numbers, int start) {
        int result = 0;
        for (int i = start; i < limit; i++) {
            result += numbers[i];
        }
        return result;
    }

    public static int[] readInput() {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        int[] result = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            result[i] = Integer.parseInt(String.valueOf(number.charAt(i)));
        }
        return result;
    }
}
