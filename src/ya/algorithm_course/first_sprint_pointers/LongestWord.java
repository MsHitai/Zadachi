package ya.algorithm_course.first_sprint_pointers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LongestWord {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        reader.readLine();
        int result = 0;
        String longestWord = "";
        String word;
        int wordLength;
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()){
            word = String.valueOf(tokenizer.nextToken());
            wordLength = word.length();
            if (wordLength > result) {
                result = wordLength;
                longestWord = word;
            }
        }
        System.out.println(longestWord);
        System.out.println(result);
    }
}
