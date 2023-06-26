package leetcode.challenges;

public class ClimbingStairs {

    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();

        System.out.println(cs.climbStairs(5));
    }

    public int climbStairs(int n) {
        if (n <= 2) { // our condition is n > 1;
            return n;
        }

        int x = 1; // we can either climb 1 or 2 steps at a time
        int y = 2;

        for (int i = 2; i < n; i++) {
            int temp = x;
            x = y;
            y = temp + y;
        }

        return y;
    }
}

/*
*You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
*
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Constraints:

1 <= n <= 45 */
