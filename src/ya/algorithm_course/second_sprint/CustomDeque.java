package ya.algorithm_course.second_sprint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <a href="https://contest.yandex.ru/contest/22781/run-report/135450040/">...</a>
 */

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Используем кольцевой буфер - храним указатели слева и справа, не сдвигая сами элементы влево или вправо, а двигая
 * только указатели головы и хвоста.

 * При любом добавлении идет сначала проверка, что дек не пуст, т.е. size < maxSize, т.к. при переполнении нужно по
 * условию выдать "error".

 * При добавлении в начало, необходимо подвинуть голову влево, так как левый край при добавлении в массив должен уходить
 * влево, чтобы оставаться крайним слева. Чтобы правильно рассчитать индекс, воспользовалась формулой
 * (head - 1 + maxSize) % maxSize, чтобы не уходить в отрицательные числа.
 * Когда забираем число из начала, берем значение по индексу головы и двигаем этот индекс вправо, т.к. теперь граница
 * смещается. Для границы справа правила обратные - добавляем по индексу хвоста и двигаем индекс вправо, а при удалении
 * сначала двигаем указатель влево (так как при добавлении мы поставили индекс в пустую ячейку, хвост указывает на null),
 * а потом достаем значение. При уменьшении индекса влево, используется та же формула (tail - 1 + maxSize) % maxSize.

 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Рассмотрим пример, сначала положим в начало 20, а потом в конец 30 и попробуем получить значение из начала и из конца.

 * Нам приходит 20, дек пуст, индекс головы равен 0. Сначала сдвигаем его, по формуле получаем (0 - 1 + 10) % 10 = 9
 * индекс, кладем туда 20.
 * Приходит элемент 30 - команда положить в конец. Индекс хвоста = 0. Мы кладем туда значение и двигаем индекс хвоста на
 * единицу вправо. В массиве лежит = [30, null, null, null, null, null, null, null, null, 20].
 * Приходит команда получить значение из начала, голова указывает на 9, там лежит 20 - возвращаем 20, по индексу 9 кладем
 * null и двигаем голову на единицу вправо с остатком от деления на maxSize, чтобы не выйти за границы массива. Индекс
 * становится 0.
 * Приходит команда забрать значение из хвоста, индекс равен 0, там 30, возвращаем значение.

 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Операция добавления в массив и чтения записи константная = О(1). Передвижение самого указателя также О(1), так как
 * мы не двигаем все значения влево, либо вправо. Итоговая сложность операций дека О(1). Чтение и проход по командам за O(n)
 */
public class CustomDeque {

    private final Integer[] items;
    private int tail;
    private int head;
    private int size;
    private final int maxSize;

    public CustomDeque(int maxSize) {
        items = new Integer[maxSize];
        size = 0;
        tail = 0;
        head = 0;
        this.maxSize = maxSize;
    }

    public void pushFront(Integer item) {
        if (size < maxSize) {
            head = (head - 1 + maxSize) % maxSize;
            items[head] = item;
            size++;
        } else {
            System.out.println("error");
        }
    }

    public void pushBack(Integer item) {
        if (size < maxSize) {
            items[tail] = item;
            tail = (tail + 1) % maxSize;
            size++;
        } else {
            System.out.println("error");
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Integer popFront() {
        if (isEmpty()) {
            return null;
        }
        Integer result = items[head];
        items[head] = null;
        head = (head + 1) % maxSize;
        size--;
        return result;
    }

    public Integer popBack() {
        if (isEmpty()) {
            return null;
        }
        tail = (tail - 1 + maxSize) % maxSize;
        Integer result = items[tail];
        items[tail] = null;
        size--;
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int commandsSize = Integer.parseInt(reader.readLine());
            int maxSize = Integer.parseInt(reader.readLine());
            String[] commands = new String[commandsSize];
            for (int i = 0; i < commandsSize; i++) {
                commands[i] = reader.readLine();
            }
            CustomDeque deque = new CustomDeque(maxSize);
            processCommands(commands, deque);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void processCommands(String[] commands, CustomDeque deque) {
        Integer temp;
        String[] split;
        for (String command : commands) {
            if (command.startsWith("push_back")) {
                split = command.split(" ");
                temp = Integer.parseInt(split[1]);
                deque.pushBack(temp);
            } else if (command.startsWith("push_front")) {
                split = command.split(" ");
                temp = Integer.parseInt(split[1]);
                deque.pushFront(temp);
            } else if (command.startsWith("pop_front")) {
                temp = deque.popFront();
                printValue(temp);
            } else {
                temp = deque.popBack();
                printValue(temp);
            }
        }
    }

    private static void printValue(Integer value) {
        if (value == null) {
            System.out.println("error");
        } else {
            System.out.println(value);
        }
    }
}
