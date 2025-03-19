package ya.algorithm_course.first_sprint;

// <template>
class Node<V> {

    public V value;

    public Node<V> next;
    public Node<V> prev;

    public Node(V value, Node<V> next, Node<V> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
// <template>

 class TestTheory {

     public static void main(String[] args) {
         Node<String> node3 = new Node<>("node3", null, null);
         Node<String> node2 = new Node<>("node2", node3, null);
         Node<String> node1 = new Node<>("node1", node2, null);
         Node<String> node0 = new Node<>("node0", node1, null);
         node1.prev = node0;
         node2.prev = node1;
         node3.prev = node2;
         Node<String> newNode = reverseTwoPointerLinkedList(node0);
         System.out.println("newNode: " + newNode);
         System.out.println("newNode.prev: " + newNode.prev);
         System.out.println("newNode.next: " + newNode.next);
         test();
     }

    public static Node<String> reverseTwoPointerLinkedList(Node<String> head) {
        Node<String> current = head;
        Node<String> temp = null;
        Node<String> newHead = null;

        while (current != null) {
            // Save the next node before swapping pointers
            temp = current.prev;
            // Swap next and prev pointers
            current.prev = current.next;
            current.next = temp;
            // Move to the next node in the original list
            newHead = current; // This becomes the new head at the end
            current = current.prev; // Use original next (now prev after swap)
        }

        return newHead;
    }

    public static void test() {
        Node<String> node3 = new Node<>("node3", null, null);
        Node<String> node2 = new Node<>("node2", node3, null);
        Node<String> node1 = new Node<>("node1", node2, null);
        Node<String> node0 = new Node<>("node0", node1, null);
        node1.prev = node0;
        node2.prev = node1;
        node3.prev = node2;
        Node<String> newNode = reverseTwoPointerLinkedList(node0);
        /* result is :*/
        System.out.println(newNode == node3);
        System.out.println(node3.next == node2);
        System.out.println(node2.next == node1);
        System.out.println(node2.prev == node3);
        System.out.println(node1.next == node0);
        System.out.println(node1.prev == node2);
        System.out.println(node0.prev == node1);
    }
}
