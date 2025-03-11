package ya.java_course.sprints.sixth;

import java.util.Scanner;

public class CheckPrinter {

    private static int findMaxLength(String[] elements) {
        int max = 0;
        for (String e : elements) {
            if (e.length() > max) {
                max = e.length();
            }
        }
        return max;
    }

    public static void printCheck(String[] items) {
        int max = 0;

        for (String item : items) {
            String[] words = item.split(", ");
            max = findMaxLength(words);
            String maxFormat = "%" + max + "s" + "  ";
            for (String word : words) {

                System.out.printf(maxFormat, word);
            }
        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество записей:");
        int n = Integer.parseInt(scanner.nextLine());
        String[] values = new String[n];
        for (int i = 0; i < n; ++i)
            values[i] = scanner.nextLine();
        printCheck(values);
    }
}
