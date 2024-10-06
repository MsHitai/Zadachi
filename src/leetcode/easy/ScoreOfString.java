package leetcode.easy;

public class ScoreOfString {

    public static void main(String[] args) {
        String s = "hello";
        String a = "zaz";
        System.out.println(scoreOfString(s));
        System.out.println(scoreOfString(a));
    }

    public static int scoreOfString(String s) {
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            result = result + Math.abs(s.charAt(i - 1) - s.charAt(i));
        }
        return result;
    }
}
