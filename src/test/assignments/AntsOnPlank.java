package test.assignments;

import java.util.Scanner;

public class AntsOnPlank {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //length n units
        int left = scanner.nextInt(); // the position of the ant which moves left
        int right = scanner.nextInt(); // the position of the ant which moves right

        System.out.println(findMinRounds(n, left, right));
    }

    public static int findMinRounds(int n, int left, int right) {
        int res = 0;
        int leftDirection = -1;
        int rightDirection = 1;

        if (right > left) {
            int a = n + 1 - right;
            int b = n + 1 - left;
            return Math.min(a, b);
        }

        while (left != -1 && left != n + 1 && right != -1 && right != n + 1) {
            res++;
            if (left + leftDirection == right) {
                leftDirection = -leftDirection;
            }
            left += leftDirection;
            if (right + rightDirection == left) {
                rightDirection = -rightDirection;
            }
            right += rightDirection;
        }

        return res;
    }
}
