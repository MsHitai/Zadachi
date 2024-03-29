package leetcode.challenges;

import java.util.List;

/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * Example 1:
 * Input: numberOfRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * Example 2:
 * Input: numRows = 1
 * Output: [[1]]
 */
public class PascalTriangle {
    
    public static void main(String[] args) {
        PascalTriangle pt = new PascalTriangle();
        int numRows = 5;
        System.out.println(pt.generate(numRows));
        int numRows1 = 1;
        System.out.println(pt.generate(numRows1));
    }

    private List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new java.util.ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new java.util.ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }
}
