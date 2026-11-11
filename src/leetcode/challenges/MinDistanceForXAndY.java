package leetcode.challenges;

/**
 * Find minimum distance between X and Y in a String, that consists of only X, O, Y.
 * If there is no X or no Y, return 0.
 * <p>
 * Один проход: запоминаем последнюю позицию X и Y (изначально -1 — "ещё не встречали").
 * Время O(n), память O(1). Тот же приём, что в LeetCode 243 "Shortest Word Distance".
 */
public class MinDistanceForXAndY {

    public static void main(String[] args) {
        System.out.println(calculateDistance("XY") == 1);
        System.out.println(calculateDistance("YOX") == 2);
        System.out.println(calculateDistance("OO") == 0);
        System.out.println(calculateDistance("OYX") == 1);
        System.out.println(calculateDistance("XOOOYOOOXOOXOOYYOOOX") == 3);
        System.out.println(calculateDistance("OOXOOYOXO") == 2);
        System.out.println(calculateDistance("OXOOOOY") == 5);
    }

    static int calculateDistance(String s) {
        int lastX = -1;
        int lastY = -1;
        int best = Integer.MAX_VALUE;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'X') {
                lastX = i;
            } else if (c == 'Y') {
                lastY = i;
            } else {
                continue;
            }
            if (lastX != -1 && lastY != -1) {
                best = Math.min(best, Math.abs(lastX - lastY));
            }
        }

        return best == Integer.MAX_VALUE ? 0 : best;
    }
}
