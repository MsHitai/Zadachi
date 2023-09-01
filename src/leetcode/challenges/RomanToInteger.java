package leetcode.challenges;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    public static void main(String[] args) {
        String s = "III"; //3
        String s1 = "CMXCVIII"; //998
        String s2 = "MCMXCIV"; //1994

        System.out.println(romanToInt(s));
        System.out.println(romanToInt(s1));
        System.out.println(romanToInt(s2));

        int[] nums = {9, 7, 1, 15};
        int target = 8;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public static int romanToInt(String s) {
        int result = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                result -= map.get(s.charAt(i));
            } else {
                result += map.get(s.charAt(i));
            }
        }

        return result;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        // In case there is no solution, we'll just return null
        return null;
    }
}
