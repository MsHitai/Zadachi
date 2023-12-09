package leetcode.challenges;

/**
 * Find minimum distance between X and Y in a String, that consists of only X, O, Y
 */
public class MinDistanceForXAndY {
    private static final String FIRST = "XY"; // must return 1
    private static final String SECOND = "YOX"; // must return 2
    private static final String THIRD = "OO"; // must return 0
    private static final String FOURTH = "OYX"; // must return 1
    private static final String FIFTH = "XOOOYOOOXOOXOOYYOOOX"; // must return 3
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
        if (!s.contains("X") || !s.contains("Y")) {
            return 0;
        }

        int indexX = 0;
        int indexY = 0;
        int minDistance = s.length();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X') {
                indexX = i;
            } else if (s.charAt(i) == 'Y') {
                indexY = i;
            }
            if (Math.abs(indexX - indexY) != 0 && Math.abs(indexX - indexY) < minDistance) {
                minDistance = Math.abs(indexX - indexY);
            }
        }

        return minDistance;
    }
}
