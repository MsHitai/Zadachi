package ya.algorithm_course.seventh_sprint_dynamic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Schedule {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            List<Lesson> lessons = new ArrayList<>();
            StringTokenizer tokenizer;
            double begin;
            double end;
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                begin = Double.parseDouble(tokenizer.nextToken());
                end = Double.parseDouble(tokenizer.nextToken());
                lessons.add(new Lesson(begin, end));
            }

            List<Lesson> scheduleMaxLessons = scheduleMaxLessons(lessons);
            System.out.println(scheduleMaxLessons.size());
            scheduleMaxLessons.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Lesson> scheduleMaxLessons(List<Lesson> lessons) {
        List<Lesson> result = new ArrayList<>();
        Collections.sort(lessons);
        Lesson first = lessons.getFirst();
        for (int i = 1; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            if (first.end > lesson.begin) {
                continue;
            }
            if (first.end <= lesson.end && first.begin <= lesson.begin) {
                result.add(first);
                first = lesson;
            }
        }
        result.add(first);

        return result;
    }

    static class Lesson implements Comparable<Lesson> {
        double begin;
        double end;

        public Lesson(double begin, double end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public String toString() {
            String beginning = formatted(begin);
            String ending = formatted(end);
            return beginning + " " + ending;
        }

        private String formatted(double value) {
            if (value == (long) value) {
                return String.format("%d", (long) value);
            } else {
                return String.format("%s", value);
            }
        }

        @Override
        public int compareTo(Lesson other) {
            int endComparison = Double.compare(this.end, other.end);
            if (endComparison != 0) {
                return endComparison;
            }
            return Double.compare(this.begin, other.begin);
        }
    }
}
