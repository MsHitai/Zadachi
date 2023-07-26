import java.util.Scanner;

public class MaxMedian {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int s = scanner.nextInt();

        int[] l = new int[n];
        int[] r = new int[n];

        for (int i = 0; i < n; i++) {
            l[i] = scanner.nextInt();
            r[i] = scanner.nextInt();
        }

        int left = 0;
        int right = s;

        int maxMedian = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            int[] count = new int[s + 1];
            for (int i = 0; i < n; i++) {
                if (l[i] > mid) {
                    continue;
                }
                count[Math.min(r[i], mid)]++;
            }

            int sum = 0;
            int median = 0;
            for (int i = s; i >= 0; i--) {
                sum += count[i];
                if (sum > n / 2) {
                    median = i;
                    break;
                }
                if (sum == n / 2 && n % 2 == 0) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (count[j] > 0) {
                            median += j;
                            break;
                        }
                    }
                    median /= 2;
                    break;
                }
            }

            if (median > maxMedian) {
                maxMedian = median;
            }

            if (sum > n / 2) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(maxMedian);
    }
}
