package leetcode.challenges;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Find minimum distance between X and Y in a String, that consists of only X, O, Y
 */
public class MinDistanceForXAndY {
    private static final String FIRST = "XY"; // must return 1
    private static final String SECOND = "YOX"; // must return 2
    private static final String THIRD = "OO"; // must return 0
    private static final String FOURTH = "XX"; // must return 0
    private static final String FIFTH = "YYYO"; // must return 0
    private static final String SIXTH = "OOXOOYOXO"; // must return 2

    public static void main(String[] args) {
        System.out.println(calculateDistance(FIRST));
        System.out.println(calculateDistance(SECOND));
        System.out.println(calculateDistance(THIRD));
        System.out.println(calculateDistance(FOURTH));
        System.out.println(calculateDistance(FIFTH));
        System.out.println(calculateDistance(SIXTH));
    }

    private static int calculateDistance(String s) {
        Set<Character> values = new HashSet<>();
        Map<Character, Integer> keys = new HashMap<>();

        if (!s.contains("X") || !s.contains("Y")) {
            return 0;
        }

        for (int i = 0; i < s.length(); i++) {

            if (values.contains(s.charAt(i)) && s.charAt(i) == 'O') {
                keys.put(s.charAt(i), keys.get(s.charAt(i)) + 1);
                continue;
            }

            if (i - 1 >= 0 && values.contains(s.charAt(i - 1)) && s.charAt(i) == 'X') {
                if (s.charAt(i - 1) == 'Y') {
                    return 1;
                }
            } else if (i - 1 >= 0 && values.contains(s.charAt(i - 1)) && s.charAt(i) == 'Y') {
                if (s.charAt(i - 1) == 'X') {
                    return 1;
                }
            }

            if (s.charAt(i) == 'X' || s.charAt(i) == 'Y') {
                values.add(s.charAt(i));
                keys.put(s.charAt(i), keys.getOrDefault(s.charAt(i), 0)); //keys.getOrDefault(s.charAt(i), 0)
            }
        }

        if (keys.get('X') != 0 && keys.get('Y') != 0) {
            return Math.min(keys.get('X'), keys.get('Y'));
        } else {
            return Math.max(keys.get('X'), keys.get('Y'));
        }
    }
}
