package leetcode.easy;

import java.io.FileNotFoundException;
import java.util.*;

@SuppressWarnings("unused")
public class EasySolutions {

    public static void main(String[] args) throws FileNotFoundException {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        int[] nums1 = new int[]{4, 2, 4, 0, 0, 3, 0, 5, 1, 0};
        int[] nums2 = new int[]{0, 0};
        moveZeroes(nums1);
        System.out.println(Arrays.toString(nums1));
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
}
