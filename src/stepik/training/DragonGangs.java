package stepik.training;

import java.util.Scanner;

/**
 * <a href="https://stepik.org/lesson/1246037/step/4?unit=1259855">...</a>
 */
public class DragonGangs {

    public static void main(String[] args) {
        int n = readInput();
        int result;

        if (n <= 2) {
            result = n;
        } else {
            result = calculateMaxPower(n);
        }

        System.out.println(result);
    }

    private static int calculateMaxPower(int n) {
        int result = 1;

        if (n % 3 == 0) {
            result = getResult(n, result);
        } else if (n % 3 == 1) {
            int times = n / 3;
            for (int i = 1; i < times; i++) {
                result = result * 3;
            }
            result = result * (3 + 1);
        } else if (n % 3 == 2) {
            result = getResult(n, result);
            result = result * 2;
        }
        return result;
    }

    private static int getResult(int n, int result) {
        int times = n / 3;
        for (int i = 1; i <= times; i++) {
            result = result * 3;
        }
        return result;
    }

    public static int readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
