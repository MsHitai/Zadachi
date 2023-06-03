import java.util.Scanner;

public class Stepik {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] people = line.split(" ");
        int[] numbers = new int[people.length];

        for (int i = 0; i < people.length; i++) {
            numbers[i] = Integer.parseInt(people[i]);
        }

        if (check(numbers)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
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
        if (count < nums.length && wrongOrder) {
            return false;
        }
        return true;
    }

    public static int randomValue() {
        int generatedValue = -3;
        try {
            java.util.Random random = new java.util.Random();
            generatedValue = random.nextInt(100) / random.nextInt(10);
        } catch (Throwable e) {
            generatedValue = -1;
        } finally {
            generatedValue = -2;
        }
        return generatedValue;
    }

    public static int calculateTime(int juniors, int seniors, int checks) {
        return juniors * checks / seniors + (juniors * checks % seniors != 0 ? 1 : 0);
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
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i+1]) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }
}
