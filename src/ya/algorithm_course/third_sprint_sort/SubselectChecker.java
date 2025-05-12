package ya.algorithm_course.third_sprint_sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SubselectChecker {

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String subselect = reader.readLine();
            String where = reader.readLine();
            if (isSubselect(subselect, where)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isSubselect(String subselect, String string) {
        int n = subselect.length();
        int j = 0;
        char first = subselect.charAt(j);
        int count = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == first) {
                count++;
                if (j + 1 < n) {
                    j++;
                    first = subselect.charAt(j);
                } else {
                    return count == n;
                }
            }
        }

        return count == n;
    }
}
