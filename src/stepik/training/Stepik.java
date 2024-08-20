package stepik.training;

import java.util.Scanner;

public class Stepik {
    public static void main(String[] args) {
        int x, y;
        String direction, result;

        String[] inputValues = readInput();
        x = Integer.parseInt(inputValues[0]);
        y = Integer.parseInt(inputValues[1]);
        direction = inputValues[2];
        int[] newCoordinates = calculateCoordinates(x, y, direction);

        result = "x: " + newCoordinates[0] + ", y: " + newCoordinates[1] + ", direction: " + direction;

        System.out.println(result);
    }

    private static int[] calculateCoordinates(int x, int y, String direction) {
        switch (direction) {
            case "down" -> {
                if (y > 0 && y < 100) {
                    y = y + 1;
                }
            }
            case "up" -> {
                if (y > 0 && y < 100) {
                    y = y - 1;
                }
            }
            case "right" -> {
                if (x > 0 && x < 100) {
                    x = x + 1;
                }
            }
            case "left" -> {
                if (x > 0 && x < 100) {
                    x = x - 1;
                }
            }
        }
        return new int[] {x, y};
    }

    public static String[] readInput() {
        String[] inputValues = new String[3];

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] values = input.split(" ");
            System.arraycopy(values, 0, inputValues, 0, 3);
        }
        scanner.close();

        return inputValues;
    }

    public static boolean check(int[] nums) {
        int count = 1;
        boolean wrongOrder = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] >= nums[i]) {
                wrongOrder = true;
                count++;
            }
        }
        return count >= nums.length || !wrongOrder;
    }

    private static int[] merge(int[] leftArray, int[] rightArray) {

        int leftSize = leftArray.length;
        int rightSize = rightArray.length;
        int[] resultArray = new int[leftSize + rightSize];

        // Индексы, по которым идёт итерация
        int left = 0;
        int right = 0;
        int index = 0;

        while (index < resultArray.length) {

            // Если левый индекс больше максимального левого индекса — добавляем элемент из правого подмассива.
            if (left >= leftSize) {
                resultArray[index] = rightArray[right];
                right++;
            }

            // Если правый индекс больше максимального — добавляем элемент из левого подмассива.
            else if (right >= rightSize) {
                resultArray[index] = rightArray[left];
                left++;
            }

            // Если оба текущих индекса внутри своих границ, нужно сравнивать элементы по этим индексам
            // Если элемент в левом массиве меньше — добавляем его и увеличиваем левый индекс.
            else if (leftArray[left] <= rightArray[right]) {
                resultArray[index] = leftArray[left];
                left++;
            }
            // Иначе — делаем то же самое с правым индексом.
            else {
                resultArray[index] = rightArray[right];
                right++;
            }
            index++;
        }
        return resultArray;
    }

    public static void bubbleSort(int[] array) {
        boolean sorted = false;
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
        }
    }
}
