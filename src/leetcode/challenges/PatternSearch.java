package leetcode.challenges;

import java.util.*;

public class PatternSearch {

    public static final String TEXT = "aaababaabaaaabaabaabaabaaababaabaaababaabaaaabaabaabaabbabaabaaababaababaabaabaabaaabbaab"; // 22
    public static final String PATTERN = "aab";



    public static void main(String[] args) {

        /*int[] array = {2, 1, 5, 4, 3, 6, 7, 9, 8};

        insertionSort(array);
        System.out.println(Arrays.toString(array));*/
        /*int count = 0;
        StringBuilder sb = new StringBuilder(TEXT);


        while (sb.indexOf(PATTERN) != -1) {
            sb.delete(0, (sb.indexOf(PATTERN) + PATTERN.length()));
            count++;
        }

        System.out.println("Строка " + PATTERN + " встретилась в тексте " + count + " раз");*/

        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(findRepeatedDnaSequences(s));
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> list = new ArrayList<>();
        HashMap<String, Integer> freqs = new HashMap<>();

        for (int i = 0; i < s.length() - 9; i++) {
            String dna = s.substring(i, i + 10);
            freqs.put(dna, freqs.getOrDefault(dna, 0) +1);
        }

        for (String substring : freqs.keySet()) {
            if (freqs.get(substring) > 1) {
                list.add(substring);
            }
        }

        return list;
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while(j >= 0 && current < array[j]) {
                array[j+1] = array[j];
                j--;
            }
            // в этой точке мы вышли, так что j так же -1
            // или в первом элементе, где текущий >= a[j]
            array[j+1] = current;
        }
    }
}
