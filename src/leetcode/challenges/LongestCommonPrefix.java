package leetcode.challenges;

import java.util.Arrays;

public class LongestCommonPrefix {

    /*Write a function to find the longest common prefix string amongst an array of strings.

    If there is no common prefix, return an empty string "".



    Example 1:

    Input: strs = ["flower","flow","flight"]
    Output: "fl"
    Example 2:

    Input: strs = ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix amongst the input strings.*/

    public String longestCommonPrefix(String[] strs) {

        StringBuilder sb = new StringBuilder();

        Arrays.sort(strs);

        String first = strs[0];
        String last = strs[strs.length - 1];

        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                return sb.toString();
            }
            sb.append(first.charAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();

        System.out.println(lcp.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));

        System.out.println(lcp.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }
}
