package stepik.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoinChange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        List<Integer> result = changeAmountOfMoney(money);
        System.out.println(result);
    }

    private static List<Integer> changeAmountOfMoney(int money) {
        int[] change = change(money);
        int j = change.length - 1;
        List<Integer> result = new ArrayList<>();
        while (money > 0 && j > 0) {
            int coin = change[j];
            if (money - coin >= 0) {
                money -= coin;
                result.add(coin);
            }
            j--;
        }
        return result;
    }

    public static int[] change(int money) {
        int[] table = new int[money + 1];
        for (int i = 1; i <= money; i++) {
            table[i] = 4; // max available coin
        }

        int[] coins = new int[]{1, 3, 4};
        for (int m = 1; m <= money; m++) {
            for (int coin : coins) {
                if (coin <= m) {
                    table[m] = Math.min(table[m], table[m - coin] + 1);
                }
            }
        }
        return table;
    }
}

