package leetcode.hard;

import java.util.Arrays;

@SuppressWarnings("unused")
public class HardSolutions {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 2};
        int[] nums2 = new int[]{1, 2, 2};
        int[] nums3 = new int[]{1, 3, 2, 2, 1};
        int[] nums4 = new int[]{1, 2, 87, 87, 87, 2, 1};

        System.out.println(candy(nums4));
        System.out.println(candy(nums3));
        System.out.println(candy(nums));
        System.out.println(candy(nums2));
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
}
