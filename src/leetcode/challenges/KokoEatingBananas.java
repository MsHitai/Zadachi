package leetcode.challenges;

import java.util.Arrays;

public class KokoEatingBananas {

    public static void main(String[] args) {

        int[] case1 = {3, 6, 7, 11};
        int h = 8;
        int[] case2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        int h3 = 6;

        System.out.println(minEatingSpeed(case1, h));
        System.out.println(minEatingSpeed(case2, h2));
        System.out.println(minEatingSpeed(case2, h3));

        String string = "film about a great white cat, where she walks around " +
                "the New York city and crushes all buildings. She is very big, like a godzilla. Then, a dog comes in the picture and here begins all " +
                "the fun. This is very good, really, try it!";

        System.out.println(string.length());

    }

    public static int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int low = 1;
        int high = piles[piles.length - 1];

        while (low < high) {
            int mid = (low + high) / 2;
            if (canEatAll(piles, mid, h)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private static boolean canEatAll(int[] piles, int speed, int h) {
        int time = 0;

        for (int pile : piles) {
            time += (pile - 1) / speed + 1;
            if (time > h) {
                return false;
            }
        }
        return true;
    }
}
