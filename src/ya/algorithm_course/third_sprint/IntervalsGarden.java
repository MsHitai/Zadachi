package ya.algorithm_course.third_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class IntervalsGarden {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            Interval[] intervals = new Interval[n];
            StringTokenizer tokenizer;
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                Interval interval = new Interval();
                interval.start = Integer.parseInt(tokenizer.nextToken());
                interval.end = Integer.parseInt(tokenizer.nextToken());
                intervals[i] = interval;
            }
            findFreeSpaces(intervals).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<Interval> findFreeSpaces(Interval[] intervals) {
        List<Interval> result = new ArrayList<>();
        mergeSort(intervals, intervals.length);
        Interval best = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < best.start && intervals[i].end < best.end) {
                continue;
            }
            if (best.end < intervals[i].start) {
                result.add(best);
                best = intervals[i];
            }
            if (best.end < intervals[i].end && best.start < intervals[i].start) {
                best.end = intervals[i].end;
            }
        }
        result.add(best);
        return result;
    }

    private static void mergeSort(Interval[] array, int arrayLength) {
        if (arrayLength < 2) {
            return;
        }
        int middle = array.length / 2;
        Interval[] left = new Interval[middle];
        Interval[] right = new Interval[arrayLength - middle];

        System.arraycopy(array, 0, left, 0, middle);
        if (arrayLength - middle >= 0) System.arraycopy(array, middle, right, 0, arrayLength - middle);
        mergeSort(left, middle);
        mergeSort(right, arrayLength - middle);

        merge(array, left, right, middle, arrayLength - middle);
    }

    private static void merge(Interval[] array, Interval[] leftArray, Interval[] rightArray, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftArray[i].start < rightArray[j].start) {
                array[k++] = leftArray[i++];
            } else if (leftArray[i].start == rightArray[j].start && (leftArray[i].end > rightArray[j].start || rightArray[j].end > leftArray[i].start)) {
                Interval interval = leftArray[i];
                interval.end = Math.max(interval.end, rightArray[j].end);
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < left) {
            array[k++] = leftArray[i++];
        }
        while (j < right) {
            array[k++] = rightArray[j++];
        }
    }


    static class Interval implements Comparable<Interval> {
        int start;
        int end;

        public Interval() {
        }

        @Override
        public String toString() {
            return start + " " + end;
        }

        @Override
        public int compareTo(Interval o) {
            return Integer.compare(this.start, o.start);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return start == interval.start && end == interval.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }
}
