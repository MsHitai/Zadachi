package leetcode.hard;


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
}
