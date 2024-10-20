package leetcode.easy;

@SuppressWarnings("unused")
public class EasySolutions {

    public static void main(String[] args) {
        int num = 3;
        int t = 2;
        System.out.println(theMaximumAchievableX(num, t));
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
}
