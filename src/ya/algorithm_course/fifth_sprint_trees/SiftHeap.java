package ya.algorithm_course.fifth_sprint_trees;

public class SiftHeap {

    public static int siftDown(int[] heap, int idx) {
        int left = 2 * idx;
        int right = 2 * idx + 1;

        if (left >= heap.length) {
            return idx;
        }

        int indexLargest = left;
        if (right < heap.length && heap[right] > heap[left]) {
            indexLargest = right;
        }

        if (heap[indexLargest] > heap[idx]) {
            int temp = heap[idx];
            heap[idx] = heap[indexLargest];
            heap[indexLargest] = temp;
            return siftDown(heap, indexLargest);
        }

        return idx;
    }

    public static int siftUp(int[] heap, int idx) {
        if (idx == 1) {
            return idx;
        }

        int parentIndex = idx / 2;

        if (heap[parentIndex] < heap[idx]) {
            int temp = heap[idx];
            heap[idx] = heap[parentIndex];
            heap[parentIndex] = temp;
            return siftUp(heap, parentIndex);
        }

        return idx;
    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        System.out.println(siftDown(sample, 2) == 5);
        int[] sample2 = {-1, 20, 5, 15, 30};
        System.out.println(siftDown(sample2, 2));
        int[] sample3 = {-1, 12, 6, 8, 3, 15, 7};
        System.out.println(siftUp(sample3, 5) == 1);
    }

    public static void main(String[] args) {
        SiftHeap.test();
    }
}
