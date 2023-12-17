package leetcode.challenges;

import lombok.extern.slf4j.Slf4j;

/**
 * Given a sorted array of distinct integers and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 */
@Slf4j
public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int[] nums2 = {1};
        int oneCase = 1;
        int firstTarget = 5;
        int secondTarget = 2;
        int thirdTarget = 7;
        int fourthTarget = 0;

        log.info("{}", searchInsert(nums, firstTarget)); // expected result is 2
        log.info("{}", searchInsert(nums, secondTarget)); // expected result is 1
        log.info("{}", searchInsert(nums, thirdTarget)); // expected result is 4
        log.info("{}", searchInsert(nums, fourthTarget)); // expected result is 0
        log.info("{}", searchInsert(nums2, oneCase)); // expected result is 0
    }

    public static int searchInsert(int[] nums, int target) {
        if (nums[nums.length - 1] < target) {
            return nums.length;
        } else if (target < nums[0]) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < target && target < nums[i + 1] && i + 1 < nums.length) {
                return i + 1;
            }
            if (nums[i] == target) {
                return i;
            }
        }
        return 0;
    }
}
