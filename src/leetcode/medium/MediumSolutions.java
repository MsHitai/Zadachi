package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class MediumSolutions {

    public static void main(String[] args) {
        ListNode head = new ListNode(18, new ListNode(6, new ListNode(10, new ListNode(3, null))));
        ListNode head2 = new ListNode(18, new ListNode(6, new ListNode(10, new ListNode(3, null))));
        long before = System.currentTimeMillis();
        System.out.println(before);
        System.out.println(insertGCD(head));
        long after = Math.abs(before - System.currentTimeMillis());
        System.out.println(after);

        System.out.println("faster way");
        before = System.currentTimeMillis();
        System.out.println(insertGreatestCommonDivisors(head2));
        System.out.println(Math.abs(before - System.currentTimeMillis()));
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

        return getNode(nodes.get(0), 1, nodes);
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
}
