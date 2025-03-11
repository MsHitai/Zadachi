package ya.java_course.sprints.sixteen;

public class RecursiveSortedCheck {

    public static void main(String[] args) {
        int[] arrayAsc = {1, 2, 5, 8, 12, 13, 20, 22, 24, 30, 32};

        int[] arrayDesc = {32, 30, 24, 22, 20, 13, 12, 8, 5, 2, 1};

        System.out.println("Индекс искомого элемента: " + searchBinaryAscending(arrayAsc, 30, 0,
                arrayAsc.length - 1));
        System.out.println("Индекс искомого элемента: " + searchBinaryDescending(arrayDesc, 30, 0,
                arrayDesc.length - 1));
        System.out.println("Индекс искомого элемента: " + searchBinary(arrayAsc, 30));
        System.out.println("Индекс искомого элемента: " + searchBinary(arrayDesc, 30));
    }

    private static int searchBinary(int[] array, int elem) {
        // возвращает 1, если массив отсортирован по возрастанию, и -1, если по убыванию
        int sort = getSortRecursive(array, 0, 0);
        if (sort > 0) {
            return searchBinaryAscending(array, elem, 0,
                    array.length - 1);
        } else if (sort < 0) {
            return searchBinaryDescending(array, elem, 0, array.length - 1);
        } else {
            return -1;
        }
    }

    private static int getSortRecursive(int[] array, int sort, int next) {
        // рекурсивно сравните каждый следующий элемент с предыдущим

        if (array[sort] > array[next + 1]) {
            return -1;
        } else {
            return +1;
        }
    }

    private static int searchBinaryAscending(int[] array, int elem, int low, int high) {
        if (high <= low) { // промежуток пуст
            return -1;
        }
        // промежуток не пуст
        int mid = low + ((high - low) / 2);
        if (array[mid] == elem) { // центральный элемент — искомый
            return mid;
        } else if (elem < array[mid]) { // искомый элемент меньше центрального — значит, следует искать в левой половине
            return searchBinaryAscending(array, elem, low, mid);
        } else { // иначе следует искать в правой половине
            return searchBinaryAscending(array, elem, mid + 1, high);
        }
    }

    private static int searchBinaryDescending(int[] array, int elem, int low, int high) {
        if (high <= low) { // промежуток пуст
            return -1;
        }
        // промежуток не пуст
        int mid = low + ((high - low) / 2);
        if (array[mid] == elem) { // центральный элемент — искомый
            return mid;
        } else if (elem < array[mid]) { // на этот раз искомый элемент больше центрального
            // все элементы больше центрального и располагаются в левой половине
            return searchBinaryDescending(array, elem, mid + 1, high);
        } else { // иначе следует искать в правой половине
            return searchBinaryDescending(array, elem, low, mid);
        }
    }
}
