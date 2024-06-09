package ya.sre_contest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TaskOne {

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int numberOfIntervals = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numberOfIntervals; i++) {
            String line = sc.nextLine();
            String[] dots = line.split(" ");
            Interval interval = new Interval(Integer.parseInt(dots[0]), Integer.parseInt(dots[1]));
            intervals.add(interval);
        }
        System.out.println(countCrossings(intervals));
        sc.close();
    }

    private static int countCrossings(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(Interval::getBeginning));
        int mostFrequentlyCrossed = intervals.get(0).getBeginning();
        int lastEnd = intervals.get(0).getEnd();
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.getBeginning() >= mostFrequentlyCrossed && interval.getBeginning() <= lastEnd) {
                mostFrequentlyCrossed = interval.getBeginning();
                lastEnd = Integer.min(lastEnd, interval.getEnd());
            }
        }

        return mostFrequentlyCrossed;
    }

    private static class Interval {
        private final int beginning;
        private final int end;

        public Interval(int beginning, int end) {
            this.beginning = beginning;
            this.end = end;
        }

        public int getBeginning() {
            return beginning;
        }

        public int getEnd() {
            return end;
        }
    }
}
