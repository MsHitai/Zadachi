package ya.algorithm_course.third_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class IntervalsGarden {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            List<Interval> intervals = new ArrayList<>();
            StringTokenizer tokenizer;
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                Interval interval = new Interval();
                interval.start = Integer.parseInt(tokenizer.nextToken());
                interval.end = Integer.parseInt(tokenizer.nextToken());
                intervals.add(interval);
            }
            intervals = findFreeSpaces(intervals);
            intervals.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<Interval> findFreeSpaces(List<Interval> intervals) {
        Collections.sort(intervals);

        for (int i = 0; i < intervals.size(); i++) {
            for (int j = i + 1; j < intervals.size(); j++) {
                Interval left = intervals.get(i);
                Interval right = intervals.get(j);
                if (left.end == right.start) {
                    left.end = Math.max(left.end, right.end);
                } else if (left.end > right.start) {
                    left.end = Math.max(left.end, right.end);
                }
            }
        }
        return removeDuplicates(intervals);
    }

    private static List<Interval> removeDuplicates(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>(intervals);
        for (int i = 0; i < intervals.size() - 1; i++) {
            Interval left = intervals.get(i);
            Interval right = intervals.get(i + 1);
            if (left.equals(right)) {
                result.remove(left);
                result.remove(right);
            } else if (left.end == right.end) {
                result.remove(right);
            }
        }
        return result;
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
