package leetcode.challenges;

public class Merge_Two_Sorted_Lists {

    public static void main(String[] args) {
        ListNode node1 = new ListNode();
        node1.add(1);
        node1.add(2);
        node1.add(4);

        System.out.println(node1);

        ListNode node2 = new ListNode();
        node2.add(1);
        node2.add(2);
        node2.add(4);

        System.out.println(node1);

        System.out.println(mergeTwoLists(node1, node2));
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public void add(int val) {
            ListNode prev = next;
            next = new ListNode(val, next);

        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return val +
                    "," + next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0, null);
        ListNode temp = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        if (list1 == null) {
            temp.next = list2;
            return dummy.next;
        }
        temp.next = list1;
        return dummy.next;
    }
}
