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

    /**
     * Бинарный поиск первой позиции, где nums[i] >= target (lower bound). O(log n).
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // ответ может быть равен длине массива
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
