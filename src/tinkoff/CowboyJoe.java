package tinkoff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CowboyJoe {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        String[] secondLine = scanner.nextLine().split(" ");

        int quantity = Integer.parseInt(firstLine[0]);
        int amount = Integer.parseInt(firstLine[1]);

        int[] prices = new int[quantity];

        for (int i = 0; i < secondLine.length; i++) {
            prices[i] = Integer.parseInt(secondLine[i]);
        }

        System.out.println(mostExpensiveCanAfford(prices, amount));
    }

    private static int mostExpensiveCanAfford(int[] prices, int money) {
        int result;
        List<Integer> canAfford = new ArrayList<>();
        for (int price : prices) {
            if (money >= price) {
                canAfford.add(price);
            }
        }

        if (canAfford.size() == 0) {
            return 0;
        }

        result = Collections.max(canAfford);

        return result;
    }
}
