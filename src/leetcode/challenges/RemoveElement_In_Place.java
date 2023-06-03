package leetcode.challenges;

import java.util.Arrays;

public class RemoveElement_In_Place {
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;

        System.out.println(removeElement(nums, val));
        System.out.println(Arrays.toString(nums));

        int[] nums1 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val1 = 2;

        System.out.println(removeElement(nums1, val1));
        System.out.println(Arrays.toString(nums1));
    }

    public static int removeElement(int[] nums, int val) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != val) {

                nums[count] = nums[i];

                count++;
            }
        }
        return count;
    }
}
/*int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;*/