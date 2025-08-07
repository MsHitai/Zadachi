package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class MediumSolutions {

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        int[] nums1 = new int[]{1, 1, 1, 1};
        int[] nums2 = new int[]{7, 7, 7, 7};
        int[] nums3 = new int[]{3, 0, 6, 1, 5};
        int[] nums4 = new int[]{1, 3, 1};
        int[] nums5 = new int[]{0, 1, 2, 3};

        /*try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int[] ints = new int[5];
            for (int i = 0; i < 5; i++) {
                ints[i] = Integer.parseInt(tokenizer.nextToken(","));
            }

            rotate(ints, 4);
            System.out.println(Arrays.toString(ints));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }*/

        System.out.println(hIndex(nums));
        System.out.println(hIndex(nums1));
        System.out.println(hIndex(nums2));
        System.out.println(hIndex(nums3));
        System.out.println(hIndex(nums4));
        System.out.println(hIndex(nums5));
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
