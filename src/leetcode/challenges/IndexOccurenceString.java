package leetcode.challenges;

public class IndexOccurenceString {

    /*Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
    or -1 if needle is not part of haystack.

    Input: haystack = "sadbutsad", needle = "sad"
    Output: 0
    Explanation: "sad" occurs at index 0 and 6.
    The first occurrence is at index 0, so we return 0.*/

    public int strStr(String haystack, String needle) {
        int index = -1;

        if (haystack.contains(needle)) {
            index = haystack.indexOf(needle);
        }

        return index;
    }

    public static void main(String[] args) {
        IndexOccurenceString occur = new IndexOccurenceString();

        System.out.println(occur.strStr("sadbutsad", "sad"));

        System.out.println(occur.strStr("leetcode", "leeto"));
    }
}
