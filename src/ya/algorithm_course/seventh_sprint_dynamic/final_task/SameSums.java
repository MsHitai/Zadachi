package ya.algorithm_course.seventh_sprint_dynamic.final_task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href="https://contest.yandex.ru/contest/25597/run-report/139514165/">...</a>
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
 * Первая цифра в массиве очков = 1, в начале заполнения все элементы массива dp = 0, поэтому все элементы заполнятся как 1,
 * так как они больше 0. Далее число 5. Смотрим, что число не равно максимуму и проверяем, что больше, текущее значение 1
 * или берем по индексу 7 - 5 = 2 в массиве dp значение 1 плюс вес 5 = 6. 6 больше 1, записываем 6. 7 Число равно максимуму,
 * его мы не будем обрабатывать. Далее число 1, после обработки 5, в предпоследней клетке также окажется значение 6, его
 * мы достанем по индексу 7 - 1 и, прибавив 1 получим 7. 7 больше 6 и равно максимуму, выдаем ответ True.
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
        int[] dp = new int[max + 1];

        for (Integer point : points) {
            for (int i = max; i >= point; i--) {
                if (point != max) {
                    dp[i] = Math.max(dp[i], dp[i - point] + point);
                }
            }
        }

        return dp[max] == max;
    }
}
