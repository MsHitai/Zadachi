package stepik.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CoverDotsByLines {

    public static void main(String[] args) {

        /*Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(); // number of intervals
        sc.nextLine();*/

        /*while(sc.hasNext()) {
            String line = sc.nextLine();
            String [] b = line.split(" "); // two dots the beginning and the end
            Interval interval = new Interval();
            interval.setBeginning(Integer.parseInt(b[0]));
            interval.setEnd(Integer.parseInt(b[1]));
            intervals.add(interval);
        }*/

        List<Interval> intervals = new ArrayList<>();

        intervals.add(new Interval(4, 7));
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 5));
        intervals.add(new Interval(5, 6)); // assertThat there are 2 dots and they are 3 and 6


        intervals.sort(Comparator.comparingInt(i -> i.beginning));
        intervals.sort(Comparator.comparingInt(i -> i.end));

        for (Interval interval : intervals) {
            System.out.println(interval.getBeginning() + " " + interval.getEnd());
        }

        List<Integer> resultDots = new ArrayList<>();
        int count = 1;

        count = findTheDots(resultDots, count, intervals);

        printResult(resultDots, count);
    }

    private static int findTheDots(List<Integer> resultDots, int count, List<Interval> intervals) {

        int j = 0;
        resultDots.add(intervals.get(0).getEnd());

        for (Interval interval : intervals) {
            if (interval.getBeginning() > resultDots.get(j)) {
                count++;
                j++;
                resultDots.add(interval.getEnd());
            }
        }

        return count;
    }

    private static void printResult(List<Integer> resultDots, int count) {
        System.out.println(count);
        StringBuilder sb = new StringBuilder();
        for (int num : resultDots) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }

    private static class Interval {
        int beginning;
        int end;

        public Interval(int beginning, int end) {
            this.beginning = beginning;
            this.end = end;
        }

        public int getBeginning() {
            return beginning;
        }

        public void setBeginning(int beginning) {
            this.beginning = beginning;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
}
