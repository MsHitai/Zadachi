package stepik.training;

public class OneStarSolutions {
    public static void main(String[] args) {
        int n = DragonGangs.readInput();

        int result = (int) Math.round((double) fact(n) / Math.E);

        System.out.println(result);
    }

    public static int fact(int n) {
        return n <= 1 ? 1 : n * fact(n - 1);
    }

    private static double getSubFactorial(int n) {
        double result = 0;
        double factorial = 1;
        int count = 0;

        for (int i = 1; i <= n; i++) {
            // factorial of the i
            factorial = factorial * i;

            // If count is even
            if (count % 2 == 0)
                result = result - (1 / factorial);
            else
                result = result + (1 / factorial);

            // Increase the value of count by 1
            count++;
        }

        return factorial * (1 + result);
    }

}
