package ya.algorithm_course.second_sprint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.Stack;

/**
 * <a href="https://contest.yandex.ru/contest/22781/run-report/135453540/">...</a>
 */

/**
 * -- ПРИНЦИП РАБОТЫ --
 * При получении строки, записанной в постфиксной нотации, необходимо действовать таким образом:
 * Если нам встречается число, мы кладем его в стек, если знак операции, мы достаем последние два числа из стека и
 * применяем пришедшую операцию к этим двум числам. Результат кладем в стек.

 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Допустим нам пришла строка 3 4 +. Последовательно проходимся по строке. Цифра 3, кладем в стек. 4 кладем в стек.
 * + это операция, идем в метод, который определяет какая операция пришла и возвращает результат действия. Получаем 7.

 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --

 * В моем алгоритме для удобства, я разделила  строку на массив строк с помощью String.split, что занимает O(n) и
 * теоретически можно было бы воспользоваться StringTokenizer, идя до первого разделителя и кладя в массив строк, но
 * так как длина строки нам изначально не дается, а метод countTokens у StringTokenizer также занимает O(n), кажется как
 * будто в этом нет большого смысла.

 * Проход по массиву, либо строке - O(n), операция вычисления константная, итого время работы алгоритма O(n).
 * Затрачена дополнительная память на сет для константной проверки (O(1)), что мы смотрим не на операцию, а на число, которое
 * можно безопасно распарсить в целое число и положить в стек. Сам стек также требует памяти, которая зависит от
 * входных данных, но не всегда линейно. В лучшем случае, встречая знак операции, мы достаем 2 числа и возвращаем обратно
 * одно, зависимость будет близкая к логарифмической. Но в худшем случае, если придет не валидное выражение, содержащие
 * только множество цифр и, например, 1 знак операции - зависимость будет линейная. 3 переменные типа int для хранения
 * первого и второго операнда и результата выражения занимают константу памяти - O(1). Таким образом, в худшем случае,
 * пространственная сложность O(n), в лучшем случае или среднем при валидных данных - O(log n).
 */
public class PolskaCalculator {

    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] statement = reader.readLine().split(" ");
            System.out.println(calculate(statement));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static int calculate(String[] statement) {
        Stack<Integer> stack = new Stack<>();
        Set<String> dictionary = Set.of("+", "-", "*", "/");
        int firstOperand;
        int secondOperand;
        int result;
        for (String str : statement) {
            if (!dictionary.contains(str)) {
                stack.push(Integer.parseInt(str));
            } else {
                secondOperand = stack.pop();
                firstOperand = stack.pop();
                result = calculate(firstOperand, secondOperand, str);
                stack.push(result);
            }
        }

        return stack.pop();
    }

    private static int calculate(int first, int second, String character) {
        switch (character) {
            case "+" -> {
                return first + second;
            }
            case "-" -> {
                return first - second;
            }
            case "*" -> {
                return first * second;
            }
            case "/" -> {
                return Math.floorDiv(first, second);
            }
            default -> throw new RuntimeException();
        }
    }
}
