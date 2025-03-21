package leetcode.hard;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class LargestRectangle {

    public static void main(String[] args) {
        LargestRectangle rectangle = new LargestRectangle();
        int[] heights = new int[]{2, 1, 2};
        int x = rectangle.largestRectArea(heights);
        System.out.println(x);
        System.out.println(x == 3);
        heights = new int[]{2, 1, 5, 6, 2, 3};
        x = rectangle.largestRectArea(heights);
        System.out.println(x);
        System.out.println(x == 10);
        heights = new int[]{2, 4};
        x = rectangle.largestRectArea(heights);
        System.out.println(x);
        System.out.println(x == 4);
    }

    public int largestRectArea(int[] heights) {
        int[] stack = new int[heights.length];
        int maxArea = 0;
        int i = 0;
        int stackIndex = 0;
        int n = heights.length;

        while (i < n || stackIndex > 0) {
            if (stackIndex == 0 || (i < n && heights[i] >= heights[stack[stackIndex - 1]])) {
                stack[stackIndex++] = i;
                i++;
            } else {
                int top = stack[--stackIndex];
                int width = stackIndex == 0 ? i : i - stack[stackIndex - 1] - 1;
                int area = heights[top] * width;
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

// doesn't work on {2, 1, 2} todo find the error and improve:
    public int largestRectangleArea(int[] heights) {
        int[] copyHeights = Arrays.copyOf(heights, heights.length + 1);
        copyHeights[copyHeights.length - 1] = 0;
        int maxArea = 0;
        int temp;
        int height;
        Deque<Integer> indexes = new LinkedList<>();
        for (int i = 0; i < copyHeights.length; i++) {
            height = copyHeights[i];
            if (indexes.isEmpty() || height >= copyHeights[indexes.getLast()]) {
                indexes.addLast(i);
            } else if (height < copyHeights[indexes.getLast()]) {
                while (!indexes.isEmpty() && height < copyHeights[indexes.getLast()]) {
                    temp = copyHeights[indexes.getLast()] * (Math.abs(i - indexes.pollLast()));
                    if (maxArea < temp) {
                        maxArea = temp;
                    }
                }
                indexes.addLast(i);
            }
        }
        return maxArea;
    }
}
