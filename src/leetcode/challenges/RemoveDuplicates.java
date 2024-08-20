package leetcode.challenges;

public class RemoveDuplicates {

    public static void main(String[] args) {

        ListNode head = new ListNode();
        head.addNode(3);
        head.addNode(3);
        head.addNode(2);
        head.addNode(1);
        head.addNode(1);

        System.out.println(head);

        System.out.println(deleteDuplicates(head));


    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;

        while (node != null && node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
                continue;
            }
            node = node.next;
        }

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        int size = 0;

        ListNode() {
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public void addNode(int val) {

            next = new ListNode(val, this.next);
            this.val = val;
            size++;
        }

        @Override
        public String toString() {
            return val +
                    "," + next;
        }
    }
}
