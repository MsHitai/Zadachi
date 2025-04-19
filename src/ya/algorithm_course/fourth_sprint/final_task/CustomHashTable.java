package ya.algorithm_course.fourth_sprint.final_task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <a href="https://contest.yandex.ru/contest/24414/run-report/136853300/">...</a>
 * <p>
 * -- ПРИНЦИП РАБОТЫ --
 * Реализация хеш-таблицы на основе массива, заданной длины, разрешающая коллизии методом цепочек. По условию задачи
 * можно было не предусматривать расширяемость хеш-таблицы и балансировку в деревья, при слишком длинных цепочках,
 * поэтому заданное кол-во команд становилось размером хеш-таблицы (maxSize) и цепочки (связанные списки) не превращались
 * в деревья. Я выбрала связь узлов одностороннюю для небольшой экономии памяти, чтобы не хранить ссылку на предыдущий
 * элемент.
 * <p>
 * Для вычисления хеш-функции взяла идею домножать целое число на простое (17), после чего брала модуль от maxSize,
 * чтобы сократить количество коллизий.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Рассмотрим логику каждого публичного метода. При методе добавления ключа-значения, сначала рассчитываем хеш по ключу
 * и находим индекс, затем проверяем, если уже лежит что-то в таблице, смотрим, если ключ тот же, обновляем значение.
 * Иначе, понимаем, что у нас коллизия и создаем новый узел, кладя его либо в поле next текущего элемента, либо, если
 * в нем уже что-то лежит, ищем первое пустое звено цепочки и записываем ключ-значение туда.
 * <p>
 * Метод получения значения по ключу. Вычисляем хеш и получаем по нему индекс. Идем в ячейку, если там ничего не лежит,
 * кидаем исключение, при его отлавливании печатаем "None". Иначе, смотрим, лежит ли в ячейке тот ключ, который мы ищем,
 * если да - возвращаем его значение. Если ключ не совпадает, но значение по индексу лежит, значит у нас коллизия. Ищем
 * по цепочке нет ли этого ключа там. Если нет - исключение, если нашли - возвращаем значение.
 * <p>
 * Метод удаления по ключу. Также, рассчитываем хеш и получаем по нему индекс. Проверяем, если по индексу в массиве пусто,
 * то кидаем исключение и печатаем "None". Если что-то лежит, проверяем, если ключ тот же, то проверяем не является
 * ли этот элемент головой цепочки, если это так, то записываем в этот элемент его следующее звено. Если у нас коллизия,
 * то ищем совпадение по ключу и переписываем значение в массиве, если ключа не нашли - кидаем исключение.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Операция добавления в массив и чтения записи константная = О(1). Вычисление хеша и получение индекса константное.
 * Поиск свободной ячейки в случае коллизии O(m), где m-число звеньев в цепочке, так как мы линейно ищем следующий
 * свободный элемент. Чтение данных и проход по командам за O(n), где n-длина списка с командами. Итоговая сложность O(n).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Хеш-таблица на основе массива кастомного объекта, содержащего две переменные (ключ-значение) и ссылку на следующий
 * элемент. Этот объект "утяжеляется" ссылкой на следующий элемент, что делает его списком, при коллизиях. Но хотя он
 * и будет увеличиваться при большем количестве входных данных, эта зависимость не 1 к 1, т.е. не O(n), так как в
 * каких-то ячейках может не возникнуть коллизии, в каких-то размер списка будет 2-3 элемента. А вот размер массива items
 * напрямую зависит от входных данных, какой размер пользователь введет, такого размера и будет выделен кусок в памяти.
 * То есть размер items линейно зависит от входных данных O(m), где m - это maxSize. Так как это максимальная величина
 * на что тратится память, можно сказать, что пространственная сложность = O(m).
 */
public class CustomHashTable {

    private final CustomPair[] items;
    private final int maxSize;

    public CustomHashTable(int maxSize) {
        this.items = new CustomPair[maxSize];
        this.maxSize = maxSize;
    }

    private long getHash(int value) {
        if (value == 0) {
            return 0;
        }
        return Math.abs(17L * value);
    }

    private int getIndex(int key) {
        long hash = getHash(key);
        return (int) (hash % maxSize);
    }

    private CustomPair getNextEmptyToPut(CustomPair item) {
        CustomPair temp = item;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp;
    }

    private int getValueByKeyIfCollision(int key, CustomPair item) {
        CustomPair temp = item;
        while (temp.next != null) {
            if (temp.key == key) {
                return temp.value;
            }
            temp = temp.next;
        }
        if (temp.key == key) {
            return temp.value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private int safelyDeleteIfCollision(int key, CustomPair item, int index) {
        CustomPair temp = item;
        while (temp.next != null) {
            if (temp.key == key) {
                safelyDeleteIfKeyEquals(temp, index);
                return temp.value;
            }
            temp = temp.next;
        }
        if (temp.key == key) {
            safelyDeleteIfKeyEquals(temp, index);
            return temp.value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void safelyDeleteIfKeyEquals(CustomPair item, int index) {
        if (item.next == null) {
            items[index] = null;
        } else {
            CustomPair temp = item.next;
            items[index] = temp;
        }
    }

    public int get(int key) {
        int index = getIndex(key);
        if (items[index] == null) {
            throw new IllegalArgumentException();
        }
        if (items[index].key == key) {
            return items[index].value;
        } else {
            return getValueByKeyIfCollision(key, items[index]);
        }
    }

    public int delete(int key) {
        int index = getIndex(key);
        if (items[index] == null) {
            throw new IllegalArgumentException();
        }
        if (items[index].key == key) {
            int value = items[index].value;
            safelyDeleteIfKeyEquals(items[index], index);
            return value;
        } else {
            return safelyDeleteIfCollision(key, items[index], index);
        }
    }

    public void put(int key, int value) {
        int index = getIndex(key);
        if (items[index] != null) {
            CustomPair item = items[index];
            if (item.key == key) {
                item.value = value;
            } else {
                if (item.next == null) {
                    item.next = new CustomPair(key, value);
                } else {
                    CustomPair nextEmpty = getNextEmptyToPut(item);
                    nextEmpty.next = new CustomPair(key, value);
                }
            }
        } else {
            items[index] = new CustomPair(key, value);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int commandsSize = Integer.parseInt(reader.readLine());
            String[] commands = new String[commandsSize];
            for (int i = 0; i < commandsSize; i++) {
                commands[i] = reader.readLine();
            }
            CustomHashTable table = new CustomHashTable(commandsSize);
            processCommands(commands, table);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void processCommands(String[] commands, CustomHashTable table) {
        int key;
        int value;
        String[] split;
        for (String command : commands) {
            try {
                if (command.startsWith("put")) {
                    split = command.split(" ");
                    key = Integer.parseInt(split[1]);
                    value = Integer.parseInt(split[2]);
                    table.put(key, value);
                } else if (command.startsWith("delete")) {
                    split = command.split(" ");
                    key = Integer.parseInt(split[1]);
                    value = table.delete(key);
                    System.out.println(value);
                } else {
                    split = command.split(" ");
                    key = Integer.parseInt(split[1]);
                    value = table.get(key);
                    System.out.println(value);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("None");
            }
        }
    }

    public static class CustomPair {
        int key;
        int value;

        CustomPair next;

        public CustomPair(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }
}
