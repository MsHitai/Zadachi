package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class HardSolutions {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] nums2 = new int[]{4, 2, 0, 3, 2, 5};
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        String[] words2 = new String[]{"What", "must", "be", "acknowledgment", "shall", "be"};
        int maxWidth = 16;

        System.out.println(fullJustify(words, maxWidth));
        System.out.println(fullJustify(words2, maxWidth));
    }

    /**
     * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth
     * characters and is fully (left and right) justified.
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        if (words.length < 2) {
            return List.of(words[0]);
        }
        String whiteSpace = " ";
        List<String> result = new ArrayList<>();
        //begin from the end, look if word plus next and space between is more than the maxWidth then add Whitespace until it's maxWidth
        // for the first entry from the end no extra space between words, but all spaces go to the right
        // for the rest distribute whitespaces accordingly
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (i == words.length - 1) {
                sb.append(words[i]);
                int j = 1;
                while (sb.length() < maxWidth) {
                    if (i - j < 0) {
                        break;
                    }
                    addWhiteSpace(words, maxWidth, sb, i, whiteSpace, j);
                    j++;
                }
                result.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return result;
    }

    private static void addWhiteSpace(String[] words, int maxWidth, StringBuilder sb, int i, String whiteSpace, int j) {
        if (sb.length() + 1 + words[i - j].length() > maxWidth) {
            while (sb.length() < maxWidth) {
                sb.append(whiteSpace);
            }
        } else {
            sb.append(whiteSpace).append(words[i - j]);
        }
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
