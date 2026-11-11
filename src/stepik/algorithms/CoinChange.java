package stepik.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Размен монет минимальным количеством (LeetCode 322 "Coin Change") с восстановлением ответа.
 * <p>
 * dp[x] — минимум монет для суммы x; dp[x] = min(dp[x - coin] + 1). Чтобы восстановить набор,
 * запоминаем, какой монетой пришли в x (lastCoin[x]), и идём назад от суммы к нулю.
 * Время O(amount * coins), память O(amount).
 * <p>
 * Жадный алгоритм здесь не работает: для монет {1, 3, 4} и суммы 6 жадный даёт 4+1+1, а оптимум 3+3.
 */
public class CoinChange {

    private static final int[] COINS = {1, 3, 4};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        List<Integer> result = change(money, COINS);
        System.out.println(result == null ? "-1" : result);
    }

    /**
     * @return список монет минимальной длины, дающий amount, или null, если набрать нельзя
     */
    static List<Integer> change(int amount, int[] coins) {
        int unreachable = Integer.MAX_VALUE;
        int[] dp = new int[amount + 1];
        int[] lastCoin = new int[amount + 1];
        Arrays.fill(dp, unreachable);
        dp[0] = 0;

        for (int sum = 1; sum <= amount; sum++) {
            for (int coin : coins) {
                if (coin <= sum && dp[sum - coin] != unreachable && dp[sum - coin] + 1 < dp[sum]) {
                    dp[sum] = dp[sum - coin] + 1;
                    lastCoin[sum] = coin;
                }
            }
        }

        if (dp[amount] == unreachable) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        for (int sum = amount; sum > 0; sum -= lastCoin[sum]) {
            result.add(lastCoin[sum]);
        }
        Collections.sort(result);
        return result;
    }
}
