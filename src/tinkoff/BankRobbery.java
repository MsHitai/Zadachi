package tinkoff;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankRobbery {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int neededSum = scanner.nextInt();
        int differentTypes = scanner.nextInt();
        scanner.nextLine();
        int[] types = new int[differentTypes];
        String[] types1 = scanner.nextLine().split(" ");

        for (int i = 0; i < types1.length; i++) {
            types[i] = Integer.parseInt(types1[i]);
        }

        stealMoney(types, neededSum);

    }

    private static void stealMoney(int[] types, int amount) {
        List<Integer> allMoney = new ArrayList<>();

        for (int type : types) {
            allMoney.add(type);
            allMoney.add(type);
        }

        int result;
        List<Integer> taken;

        for (int i = 0; i < allMoney.size(); i++) {
            taken = new ArrayList<>();
            result = 0;

            for (int j = i + 1; j < allMoney.size(); j++) {
                if (allMoney.get(j) < amount) {
                    result += allMoney.get(j);
                    taken.add(allMoney.get(j));
                }
                if (result == amount) {
                    System.out.println(taken.size());
                    String formattedString = taken.toString()
                            .replace(",", "")
                            .replace("[", "")
                            .replace("]", "")
                            .trim();
                    System.out.println(formattedString);
                    return;
                }
                if (allMoney.get(j) == amount) {
                    System.out.println(1);
                    System.out.println(allMoney.get(j));
                    return;
                }
            }
        }

        System.out.println("-1");
    }
}
