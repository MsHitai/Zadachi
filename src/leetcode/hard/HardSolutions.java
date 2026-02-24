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
            String word = words[0];
            if (word.length() < maxWidth) {
                word = word + " ".repeat(maxWidth - word.length());
            }
            return List.of(word);
        }

        List<List<String>> lines = new ArrayList<>();
        int start = 0;

        while (start < words.length) {
            int end = start + 1;
            int lineLength = words[start].length();

            while (end < words.length && lineLength + 1 + words[end].length() <= maxWidth) {
                lineLength += 1 + words[end].length();
                end++;
            }

            List<String> currentLine = new ArrayList<>(Arrays.asList(words).subList(start, end));
            lines.add(currentLine);
            start = end;
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (i != lines.size() - 1) {
                result.add(formatLine(lines.get(i), maxWidth, false));
            } else {
                result.add(formatLine(lines.get(i), maxWidth, true));
            }
        }
        return result;
    }

    private static String formatLine(List<String> words, int maxWidth, boolean isLastLine) {
        if (isLastLine || words.size() == 1) {
            // Левое выравнивание
            String line = String.join(" ", words);
            return line + " ".repeat(maxWidth - line.length());
        } else {
            // Полное выравнивание
            int sumOfWordLengths = words.stream().mapToInt(String::length).sum();
            int totalSpaces = maxWidth - sumOfWordLengths;
            int gaps = words.size() - 1;
            int baseSpaces = totalSpaces / gaps;
            int extraSpaces = totalSpaces % gaps;

            StringBuilder sb = new StringBuilder();
            sb.append(words.getFirst());

            for (int i = 1; i < words.size(); i++) {
                int spacesToAdd = baseSpaces + (i <= extraSpaces ? 1 : 0);
                sb.append(" ".repeat(spacesToAdd)).append(words.get(i));
            }

            return sb.toString();
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
