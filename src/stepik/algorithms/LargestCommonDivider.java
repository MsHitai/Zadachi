package stepik.algorithms;

public class LargestCommonDivider {

    public static void main(String[] args) {
        /*Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();*/

        int a = 18;
        int b = 35;

        int c = 3_918_848;
        int d = 1_653_264;

        int e = 14_159_572;
        int f = 63_967_072;

        System.out.println(improvedCommonDivider(a, b));
        System.out.println(improvedCommonDivider(c, d));
        System.out.println(improvedCommonDivider(f, e));
    }

    private static int improvedCommonDivider(int a, int b) {
        while (a != 0 && b != 0) {
            if (a >= b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return Math.max(a, b);
    }

    private static int findCommonDivider(int a, int b) {
        if (a == 0) {
            return b;
        } else if (b == 0) {
            return a;
        } else if (a == b) {
            return b;
        } else if (a >= b) {
            while (a != 0) {
                a = a % b;
                if (b > a && a != 0) {
                    b = b % a;
                }
                if (b == 0) {
                    break;
                }
            }
            return b;
        } else {
            while (b != 0) {
                b = b % a;
                if (a > b && b != 0) {
                    a = a % b;
                }
                if (a == 0) {
                    break;
                }
            }
            return a;
        }
    }


}
