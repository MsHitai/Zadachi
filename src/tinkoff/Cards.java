package tinkoff;

import java.util.Arrays;
import java.util.Scanner;

public class Cards {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cards = scanner.nextLine();
        String[] givenCards1 = scanner.nextLine().split(" ");
        String[] winningCards1 = scanner.nextLine().split(" ");

        int numberOfCards = Integer.parseInt(cards);

        int[] givenCards = new int[numberOfCards];

        for (int i = 0; i < givenCards1.length; i++) {
            givenCards[i] = Integer.parseInt(givenCards1[i]);
        }

        int[] winningCards = new int[numberOfCards];

        for (int i = 0; i < winningCards1.length; i++) {
            winningCards[i] = Integer.parseInt(winningCards1[i]);
        }

        System.out.println(canWin(givenCards, winningCards));
    }

    private static String canWin(int[] cards, int[] winningCards) {
        int[] toCompare = new int[cards.length];

        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != winningCards[i]) {
                if (i + 1 < cards.length && cards[i + 1] == winningCards[i] ||
                        cards[i] == winningCards[i - 1]) {
                    toCompare[i] = winningCards[i];
                }
            }

            if (cards[i] == winningCards[i]) {
                toCompare[i] = cards[i];
            }
        }

        if (Arrays.equals(toCompare, winningCards)) {
            return "YES";
        } else {
            return "NO";
        }
    }
}
