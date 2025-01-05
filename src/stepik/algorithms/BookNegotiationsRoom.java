package stepik.algorithms;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@SuppressWarnings("unused")
public class BookNegotiationsRoom {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        List<Interval> intervals = new ArrayList<>();
        while (scanner.hasNext()) {
            int begin = scanner.nextInt();
            int end = scanner.nextInt();
            Interval interval = new Interval();
            interval.setBegin(begin);
            interval.setEnd(end);
            intervals.add(interval);
        }
        intervals.sort(Comparator.comparing(Interval::getEnd));
        System.out.println(findMaxBookings(intervals));
    }

    private static int findMaxBookings(List<Interval> intervals) {
        int count = 1;
        int currentEnd = intervals.get(0).getEnd();

        for (Interval interval : intervals) {
            if (interval.getBegin() > currentEnd) {
                count++;
                currentEnd = interval.getEnd();
            }
        }
        return count;
    }


    @Data
    @EqualsAndHashCode
    private static class Interval {
        private int begin;
        private int end;
    }
}
