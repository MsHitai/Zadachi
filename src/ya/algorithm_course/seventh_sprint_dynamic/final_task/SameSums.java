package ya.algorithm_course.seventh_sprint_dynamic.final_task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href="https://contest.yandex.ru/contest/25597/run-report/139548554/">...</a>
 * /**
 * -- ПРИНЦИП РАБОТЫ --
 * В своем алгоритме я строю массив dp, где проверяю максимально возможную сумму для текущего элемента из массива с очками,
 * который для удобства описания буду называть "вес". То есть идея в том, чтобы найти можем ли мы получить сумму, сложив
 * остальные элементы, без учета самого "веса", ведь мы не можем разделить его на две группы.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Сначала я не учла, что максимум может быть больше максимального "веса" в массиве очков, поэтому поняла, что нужно
 * считать максимальную сумму всех элементов и брать половину, как максимум возможной суммы. В случае, если общая сумма
 * нечетная, можно сразу отдавать ответ false, так как мы не можем разделить на 2 группы числа, чтобы в сумме они дали
 * нечетное число (как пример 15, которое можно разложить на 7 и 8, либо 10 и 5, в любом случае видно, что ни та ни та
 * пара не равна друг другу).
 * <p>
 * Проверим алгоритм на первом тест-кейсе - 1 5 7 1. И попробуем построить массив dp. Общая сумма 14, максимум для группы
 * 7, строим массив dp включительно до этого элемента и начинаем его заполнять с конца (ведь нас интересует максимум).
 * Добавляем базовый кейс после оптимизации на boolean, кладем dp[0] = true, так как мы можем получить сумму 0 для пустого
 * массива. Первая цифра в массиве очков = 1, в начале заполнения все элементы массива равны false, кроме нулевого элемента.
 * Идем с конца от максимальной суммы 7 до "веса" 1. Когда i-тый элемент у нас равен 1, мы получим такое условие
 * dp[i] = dp [1] || dp [1 - 1 = 0], и получаем true. Проверяем 5. Когда i будет равно 6, мы попадем по индексу 6 - 5 в
 * dp[1], который только что записали как true, запишем в dp[6] true, для индекса 5 - 5, мы попадем в dp[0] и запишем в
 * dp[5] true. Для i равного 7 мы попадем в ячейку dp[0] (по расчету 7 - 7) и запишем в ячейку 7 true. Так как мы можем
 * набрать сумму из 7 очков, взяв только один элемент 7.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Итоговая сложность алгоритма составляет O (n * m) - длина массива очков, умноженная на длину массива dp, которая составит
 * половину от общей суммы чисел плюс 1.
 * Все остальные операции (считывание данных, нахождение суммы в массиве очков составляет O(n), где n - длина массива очков,
 * что является меньше, чем O (n * m)).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Размер dp равен O (m), где m - это половина от общей суммы чисел плюс 1. Массив очков O (n). Несколько переменных int и
 * boolean составляют O(1). Итоговая пространственная сложность будет O (n) + O (m).
 */
public class SameSums {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> points = new ArrayList<>(n);
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                points.add(Integer.parseInt(tokenizer.nextToken()));
            }
            boolean hasTwoEqualSums = hasTwoEqualSums(points);
            if (hasTwoEqualSums) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean hasTwoEqualSums(List<Integer> points) {
        int total = points.stream().mapToInt(m -> m).sum();
        if (total % 2 != 0) {
            return false;
        }
        int max = total / 2;
        boolean[] dp = new boolean[max + 1];
        dp[0] = true;

        for (int point : points) {
            for (int i = max; i >= point; i--) {
                dp[i] = dp[i] || dp[i - point];
            }
        }

        return dp[max];
    }
}
