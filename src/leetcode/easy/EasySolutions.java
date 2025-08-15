package leetcode.easy;

import java.io.FileNotFoundException;
import java.util.*;

@SuppressWarnings("unused")
public class EasySolutions {

    public static void main(String[] args) throws FileNotFoundException {
        int[] nums = new int[]{1, 0, 0, 0, 1};
        int[] nums1 = new int[]{1, 0, 0, 0, 0};
        int[] nums2 = new int[]{0, 0, 1, 0, 1};
        int[] nums3 = new int[] {0};
        int[] nums4 = new int[] {0, 0};

        System.out.println(canPlaceFlowers(nums4, 2));
        System.out.println(canPlaceFlowers(nums3, 1));
        System.out.println(canPlaceFlowers(nums1, 2));
        System.out.println(canPlaceFlowers(nums, 1));
        System.out.println(canPlaceFlowers(nums, 2));
        System.out.println(canPlaceFlowers(nums2, 1));
    }

    public static int strStr(String haystack, String needle) {
        if (haystack.contains(needle)) {
            return haystack.indexOf(needle);
        }
        return -1;
    }

    /**
     * Return true if n flowers can be planted. They can be planted only if there is no adjacent flowers ([0], [0,0] and [0,0,0])
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length < 2) {
            return flowerbed[0] == 0 || n == 0;
        }
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (i == 0) {
                if (flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                    count++;
                    i++;
                }
                continue;
            }
            if (i + 1 < flowerbed.length && flowerbed[i - 1] == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                count++;
                i++;
            } else if (i + 1 >= flowerbed.length && flowerbed[i - 1] == 0 && flowerbed[i] == 0) {
                count++;
            }
        }
        return count >= n;
    }

    public List<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        }
        Map<String, String> DICT = new HashMap<>();
        DICT.put("2", "abc");
        DICT.put("3", "def");
        DICT.put("4", "ghi");
        DICT.put("5", "jkl");
        DICT.put("6", "mno");
        DICT.put("7", "pqrs");
        DICT.put("8", "tuv");
        DICT.put("9", "wxyz");
        List<String> variations = new ArrayList<>();
        for (String num : digits.split("")) {
            variations.add(DICT.get(num));
        }
        combine(variations, "", 0, result);
        return result;
    }

    private void combine(List<String> letters, String combine, int i, ArrayList<String> result) {
        if (combine.length() == letters.size()) {
            result.add(combine);
            return;
        }

        if (i < letters.size()) {
            String let1 = letters.get(i);
            for (int k = 0; k < let1.length(); k++) {
                String letter = String.valueOf(let1.charAt(k));
                combine(letters, combine + letter, i + 1, result);
            }
        }
    }

    public static boolean isValid(String s) {
        if (s.isEmpty()) {
            return true;
        }
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put(")", "(");
        dictionary.put("}", "{");
        dictionary.put("]", "[");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String bracket = String.valueOf(s.charAt(i));
            if (!dictionary.containsKey(bracket)) {
                stack.addLast(bracket);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                String bracketInStack = stack.getLast();
                if (bracketInStack.equals(dictionary.get(bracket))) {
                    stack.removeLast();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static int findCombinationsWithRepetitions(int n, int k) {
        int numerator = k + n - 1;
        int denumerator = factorial(n - 1) * factorial(k);
        return factorial(numerator) / denumerator;
    }

    private static int findCombinations(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public static int theMaximumAchievableX(int num, int t) {
        return num + (2 * t);
    }

    public static int scoreOfString(String s) {
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            result = result + Math.abs(s.charAt(i - 1) - s.charAt(i));
        }
        return result;
    }

    public static int[] plusOneShort(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static int[] plusOne(int[] digits) {
        increase(digits);
        return Arrays.stream(digits)
                .mapToObj(String::valueOf)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void increase(int[] digits) {
        int result = digits[digits.length - 1];
        if (result != 9 || digits.length == 1) {
            int digit = digits[digits.length - 1];
            digits[digits.length - 1] = digit + 1;
            return;
        }
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9 && i != 0) {
                digits[i] = 0;
                continue;
            }
            if (digits[i] != 9) {
                digits[i] = digits[i] + 1;
                break;
            }
            if (i == 0) {
                int value = digits[i] == 9 ? 10 : digits[i] + 1;
                digits[i] = value;
            }
        }
    }

    public static int[] getConcatenation(int[] nums) {
        int[] result = new int[nums.length * 2];
        result = Arrays.copyOf(nums, result.length);
        int j = nums.length;
        for (int num : nums) {
            result[j] = num;
            j++;
        }
        return result;
    }

    public static void moveZeroes(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int j = 1;
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if (j < nums.length && nums[i] == 0) {
                temp = nums[i];
                while (j < nums.length && nums[j] == 0) {
                    j++;
                }
                if (j < nums.length) {
                    nums[i] = nums[j];
                    nums[j] = temp;
                    j++;
                }
            }
        }
    }

    /**
     * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all
     * non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
     *
     * @param s input
     * @return true, if valid
     */
    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int start = 0;
        int last = s.length() - 1;
        while (start <= last) {
            char currFirst = s.charAt(start);
            char currLast = s.charAt(last);
            if (!Character.isLetterOrDigit(currFirst)) {
                start++;
            } else if (!Character.isLetterOrDigit(currLast)) {
                last--;
            } else {
                if (Character.toLowerCase(currFirst) != Character.toLowerCase(currLast)) {
                    return false;
                }
                start++;
                last--;
            }
        }
        return true;
    }

    /**
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] copy = new int[m];
        System.arraycopy(nums1, 0, copy, 0, m);
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < m && j < n) {
            if (copy[i] <= nums2[j]) {
                nums1[k++] = copy[i++];
            } else {
                nums1[k++] = nums2[j++];
            }
        }

        while (i < m) {
            nums1[k++] = copy[i++];
        }
        while (j < n) {
            nums1[k++] = nums2[j++];
        }
    }

    /**
     * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
     * element appears only once.
     */
    public static int removeDuplicates(int[] nums) {
        int j = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }

        return j + 1;
    }

    /**
     * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique
     * element appears at most twice.
     */
    public static int removeDuplicatesAllowTwo(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int j = 2;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[j - 2]) {
                nums[j++] = nums[i];
            }
        }

        return j;
    }

    /**
     * Given an array nums of size n, return the majority element.
     */
    public static int majorityElement(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }
        Map<Integer, Integer> counts = new HashMap<>();
        int minCount = nums.length / 2;
        int result = -1;
        int count = 0;

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);

            if (counts.get(num) >= minCount && counts.get(num) > count) {
                result = num;
                count = counts.get(num);
            }
        }

        return result;
    }

    /**
     * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the
     * future to sell that stock. Return the maximum profit you can achieve from this transaction.
     * If you cannot achieve any profit, return 0.
     */
    public static int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];
        int minIndex = 0;

        for (int i = 0; i < prices.length; i++) {
            if (i + 1 < prices.length && prices[i] < prices[i + 1]) {
                if (prices[minIndex] > prices[i]) {
                    minIndex = i;
                }
            } else {
                dp[i] = prices[i] - prices[minIndex];
            }
        }

        OptionalInt max = Arrays.stream(dp).max();
        if (max.isPresent()) {
            return max.getAsInt();
        }
        return 0;
    }

    public static int maxProfitNoDp(int[] prices) {
        int buy = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else if (prices[i] - buy > profit) {
                profit = prices[i] - buy;
            }
        }
        return profit;
    }

    public int lengthOfLastWord(String s) {
        int count = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (count == 0 && s.charAt(i) == ' ') {
                continue;
            }
            if (s.charAt(i) != ' ') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     */
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> complements = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int complement = target - num;
            if (complements.containsKey(complement)) {
                return new int[]{i, complements.get(complement)};
            }
            complements.put(num, i);
        }
        return null;
    }
}
