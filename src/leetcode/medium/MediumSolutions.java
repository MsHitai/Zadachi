package leetcode.medium;

import java.util.*;

@SuppressWarnings("unused")
public class MediumSolutions {

    public static void main(String[] args) {
        String s1 = "PAYPALISHIRING";
        System.out.println(convert("ABCD", 2));
        System.out.println(convert(s1, 3));
        System.out.println(convert(s1, 4));
        System.out.println(convert(s1, s1.length()));
    }

    public static String reverseWordsWithLibraryMethods(String s) {
        List<String> words = new ArrayList<>(Arrays.stream(s.split(" "))
                .filter(word -> !word.isBlank())
                .toList());
        StringBuilder sb = new StringBuilder();
        for (int i = words.size() - 1; i >= 0; i--) {
            sb.append(words.get(i)).append(" ");
        }
        return sb.toString().trim();
    }

    public static String reverseWords(String s) {
        List<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            } else {
                if (!sb.isEmpty()) {
                    words.add(sb.reverse().toString());
                    sb = new StringBuilder();
                }
            }
        }

        if (!sb.isEmpty()) {
            words.add(sb.reverse().toString());
        }

        return String.join(" ", words);
    }

    public static String convert(String s, int numRows) {
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        List<StringBuilder> sbs = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            sbs.add(new StringBuilder());
        }
        int i = 0;
        int j = 0;
        boolean up = true;

        while (i < s.length()) {
            sbs.get(j).append(s.charAt(i));

            if (up) {
                j++;
                if (j == numRows - 1) {
                    up = false;
                }
            } else {
                j--;
                if (j == 0) {
                    up = true;
                }
            }
            i++;
        }

        return String.join("", sbs);
    }

    public String convertWithArray(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        int direction = 1;

        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);
            currentRow += direction;

            if (currentRow == 0 || currentRow == numRows - 1) {
                direction *= -1;
            }
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    public static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();

        HashMap<Integer, String> map = new LinkedHashMap<>();

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            int key = entry.getKey();
            String symbol = entry.getValue();
            while (key <= num) {
                result.append(symbol);
                num -= key;
            }
        }

        return result.toString();
    }

    /**
     * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the
     * circuit once in the clockwise direction, otherwise return -1.
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalDif = 0;
        int tank = 0;
        int start = 0;

        for (int i = 0; i < cost.length; i++) {
            totalDif += gas[i] - cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                tank = 0;
                start = i + 1;
            }
        }

        return totalDif >= 0 ? start : -1;
    }

    /**
     * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all
     * the elements of nums except nums[i].
     */
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        if (Arrays.equals(nums, answer)) {
            return answer;
        }
        int multiplyAll = 1;
        boolean hasZero = false;
        boolean hasNonZero;
        int countNonZero = 0;
        Set<Integer> indexZero = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                hasZero = true;
                indexZero.add(i);
                continue;
            }
            multiplyAll *= num;
            countNonZero++;
        }

        hasNonZero = countNonZero >= indexZero.size();

        for (int i = 0; i < n; i++) {
            if (hasZero && indexZero.contains(i) && hasNonZero && indexZero.size() < 2) {
                answer[i] = multiplyAll;
            } else if (!hasZero) {
                answer[i] = multiplyAll / nums[i];
            }
        }

        return answer;
    }

    public static double averageWaitingTime(int[][] customers) {
        List<Integer> waitingTimes = new ArrayList<>();
        int currentTime = 0;

        for (int i = 0; i < customers.length; i++) {
            int[] row = customers[i];
            if (i == 0) {
                currentTime += row[0] + row[1];
            } else if (currentTime < row[0]) {
                currentTime = row[0] + row[1];
            } else {
                currentTime += row[1];
            }
            waitingTimes.add(currentTime - row[0]);
        }
        double sum = waitingTimes.stream().mapToDouble(n -> n).sum();

        return sum / waitingTimes.size();
    }

    /**
     * Arrival time and service time is given in the arrays. Return the average waiting time of all customers.
     */
    public static double averageWaitingTimeNoList(int[][] customers) {
        double totalWaitingTime = 0;
        int currentTime = 0;

        for (int[] customer : customers) {
            int arrival = customer[0];
            int service = customer[1];
            if (currentTime < arrival) {
                currentTime = arrival;
            }
            int waiting_time = currentTime - arrival + service;
            totalWaitingTime += waiting_time;
            currentTime += service;
        }

        return totalWaitingTime / customers.length;
    }

    /**
     * Given an array of positive integers nums and a positive integer target, return the minimal length of a
     * subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int sum = 0;
        int bestLength = Integer.MAX_VALUE;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];

            while (sum >= target) {
                int windowLength = end - start + 1;
                bestLength = Math.min(bestLength, windowLength);
                sum -= nums[start];
                start++;
            }
        }

        return bestLength == Integer.MAX_VALUE ? 0 : bestLength;
    }

    /**
     * Find h-index. The h-index is defined as the maximum value of h such that the given researcher has published
     * at least h papers that have each been cited at least h times.
     */
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int[] copy = new int[citations.length];
        int j = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            copy[j] = citations[i];
            j++;
        }
        int ans = 0;

        for (int i = 0; i < copy.length; i++) {
            if (copy[i] >= i + 1) {
                ans++;
            }
        }

        return ans;
    }

    public static ListNode insertGreatestCommonDivisors(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            int first = temp.getVal();
            int second = temp.getNext() != null ? temp.getNext().getVal() : -1;
            int commonDivider = -1;
            if (second != -1) {
                commonDivider = greatestCommonDivider(first, second);
            }
            if (commonDivider != -1) {
                nodes.add(new ListNode(first, new ListNode(commonDivider)));
            } else {
                nodes.add(new ListNode(first, null));
            }
            temp = temp.getNext();
        }

        return getNode(nodes.getFirst(), 1, nodes);
    }

    public static ListNode getNode(ListNode node, int index, List<ListNode> nodes) {
        if (node == null || nodes.size() <= index) {
            return node;
        }
        node.getNext().setNext(getNode(nodes.get(index), index + 1, nodes));
        return node;
    }

    private static int greatestCommonDivider(int first, int second) {
        while (first != 0 && second != 0) {
            if (first >= second) {
                first = first % second;
            } else {
                second = second % first;
            }
        }
        return Math.max(first, second);
    }

    private static int greatestCommonDividerRecursive(int first, int second) {
        if (second == 0) {
            return first;
        }
        return greatestCommonDividerRecursive(second, first % second);
    }

    public static ListNode insertGCD(ListNode head) {
        if (head.getNext() == null) {
            return head;
        }
        ListNode before = head;
        ListNode next = head.getNext();
        while (before.getNext() != null) {
            ListNode node = new ListNode();
            node.setVal(greatestCommonDividerRecursive(before.getVal(), next.getVal()));
            before.setNext(node);
            node.setNext(next);
            before = next;
            next = next.getNext();
        }
        return head;
    }

    /**
     * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
     */
    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) {
            return;
        }

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        int temp;
        while (start < end) {
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    public static boolean canJump(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
        boolean[] dp = new boolean[nums.length];
        dp[0] = nums[0] != 0;
        int temp;

        for (int i = 0; i < nums.length; i++) {
            if (dp[i]) {
                temp = 1;
                while (temp <= nums[i] && i + temp < nums.length) {
                    dp[i + temp] = true;

                    temp++;
                }
            }
        }

        return dp[nums.length - 1];
    }

    /**
     * Return true if you can reach the last index, starting from first (each value is max jump length) or false otherwise.
     */
    public static boolean canJumpNoDp(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reachable) {
                return false;
            }
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }

    /**
     * Return the minimum number of jumps to reach index n - 1. Dynamic programming
     */
    public static int jumpDp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= nums[i] && i + j < n; j++) {
                if (dp[i + j] == 0) {
                    dp[i + j] = dp[i] + 1;
                }
            }
        }
        return dp[n - 1];
    }

    /**
     * Return the minimum number of jumps to reach index n - 1. Greedy
     */
    public static int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }
}
