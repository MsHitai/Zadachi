package ya.algorithm_course;

public class MockInterviewTask {

/*
Задача 1.
Дан массив из n не отрицательных чисел и число 1 <= k <= n.
Нужно разбить массив на k отрезов так, чтобы максимальная сумма среди этих отрезков была минимальна
Вернуть нужно сумму
Примеры:

k=1
arr=1 1 1 1 1
Ответ=5
k=2
arr=1 1 | 1 1 1
Ответ=3
k=4
arr=1 3 2 4 | 10 | 8 4 | 2 5 3
Ответ=12

*/

    public static void main(String[] args) {
        int[] arr = new int[]{7, 2, 5, 10, 8};
        System.out.println(findSum(2, arr)); //expected 18
    }

    public static int findSum(int k, int[] nums) {
        int left = nums[0];
        int right = 0;

        for (int i : nums) {
            left = Math.max(left, i);
            right += i;
        }
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (check(k, nums, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /*
      check возвращает true если массив arr можно разбить на k кусочков так, что сумма каждого кусочка меньше или равна c
    */
    public static boolean check(int k, int[] arr, int c) {
        long sum = 0L;
        int count = 1;

        for (int num : arr) {
            if ((sum + num) <= c) {
                sum += num;
            } else {
                count += 1;
                sum = num;
            }
        }
        return count <= k;
    }
}
