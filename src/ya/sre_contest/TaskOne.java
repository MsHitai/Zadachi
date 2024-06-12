package ya.sre_contest;

import java.util.*;

public class TaskOne {

    public static void main(String[] args) {
        Map<Integer, Integer> intervals = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        int numberOfIntervals = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numberOfIntervals; i++) {
            String line = sc.nextLine();
            String[] dots = line.split(" ");
            int start = Integer.parseInt(dots[0]);
            int end = Integer.parseInt(dots[1]);
            intervals.put(start, intervals.getOrDefault(start, 0) + 1);
            intervals.put(end, intervals.getOrDefault(end, 0) - 1);
        }
        System.out.println(countCrossings(intervals));
        sc.close();
    }

    private static int countCrossings(Map<Integer, Integer> intervals) {
        int answer = 0;
        int best = 0;
        int current = 0;
        for (Integer key : intervals.keySet()) {
            current += intervals.get(key);
            if (current > best) {
                answer = key;
                best = current;
            }
        }

        return answer;
    }
}
