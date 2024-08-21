package stepik.training;

import java.util.Scanner;

/**
 * <a href="https://stepik.org/lesson/1246037/step/3?unit=1259855">...</a>
 */
public class DragonSlayer {

    public static void main(String[] args) {
        Triple<Integer, Integer, Integer> triple = readInput();
        int swordAttack = triple.first();//n
        int dragonHeads = triple.second();//m
        int dragonRecovery = triple.third();//k
        int result = countNumberOfHits(swordAttack, dragonHeads, dragonRecovery);

        System.out.println(result);
    }

    private static int countNumberOfHits(int swordAttack, int dragonHeads, int dragonRecovery) {
        int count = 0;
        if (swordAttack < dragonRecovery && swordAttack != dragonHeads) {
            return -1;
        }

        while (dragonHeads != 0) {
            dragonHeads = dragonHeads - swordAttack;
            count++;
            if (dragonHeads < 0) {
                break;
            }
            if (dragonHeads != 0) {
                dragonHeads += dragonRecovery;
            }
        }

        return count;
    }

    private static Triple<Integer, Integer, Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().trim().split(" \\| ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);
        return new Triple<>(n, m, k);
    }
}

record Triple<A, B, C>(A first, B second, C third) {
}
