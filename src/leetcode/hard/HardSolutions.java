package leetcode.hard;

import java.util.Arrays;

@SuppressWarnings("unused")
public class HardSolutions {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] nums2 = new int[]{4, 2, 0, 3, 2, 5};
        int[] nums3 = new int[]{1, 3, 2, 2, 1};
        int[] nums4 = new int[]{1, 2, 87, 87, 87, 2, 1};

        System.out.println(trapTwoPointers(nums));
        System.out.println(trapTwoPointers(nums2));
    }

    /**
     * Каждому ребенку 1 конфета, у кого рейтинг выше, чем у соседей - тому плюс 1
     */
    public static int candy(int[] ratings) {
        int n = ratings.length;
        if (n < 2) {
            return n;
        }
        int[] sums = new int[n];
        Arrays.fill(sums, 1);
        int count = 0;

        for (int i = 0; i < n - 1; i++) {
            if (ratings[i] < ratings[i + 1]) {
                sums[i + 1] = sums[i] + 1;
            }
        }

        for (int i = n - 1; i > 0; i--) {
            if (ratings[i] < ratings[i - 1]) {
                sums[i - 1] = Math.max(sums[i - 1], sums[i] + 1);
            }
            count += sums[i - 1];
        }

        return count + sums[n - 1];
    }

    /**
     * Сколько соберется воды между столбцами
     */
    public static int trapTwoPointers(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int leftMax = 0;
        int rightMax = 0;
        int res = 0;
        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    res += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    res += rightMax - height[right];
                }
                right--;
            }
        }
        return res;
    }

    /**
     * Сколько соберется воды между столбцами используя стек
     */
    public static int trapWithStack(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] stack = new int[n];
        int stackIndex = 0;
        int totalWater = 0;

        for (int i = 0; i < n; i++) {
            while (stackIndex > 0 && height[i] > height[stack[stackIndex - 1]]) {
                int top = stack[--stackIndex];

                if (stackIndex == 0) {
                    break;
                }

                int distance = i - stack[stackIndex - 1] - 1;
                int boundedHeight = Math.min(height[i], height[stack[stackIndex - 1]]) - height[top];

                if (boundedHeight > 0) {
                    totalWater += boundedHeight * distance;
                }
            }
            stack[stackIndex++] = i;
        }

        return totalWater;
    }
}
