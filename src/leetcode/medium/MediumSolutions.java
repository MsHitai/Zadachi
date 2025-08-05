package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class MediumSolutions {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        int[] nums1 = new int[]{3, 2, 1, 0, 4};
        int[] nums2 = new int[]{0};

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

        System.out.println(canJumpNoDp(nums));
        System.out.println(canJumpNoDp(nums1));
        System.out.println(canJumpNoDp(nums2));
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
}
