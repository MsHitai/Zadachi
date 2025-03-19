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
         test();
     }

    public static Node<String> reverseTwoPointerLinkedList(Node<String> head) {
        Node<String> current = head;
        Node<String> temp;
        Node<String> newHead = null;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            newHead = current;
            current = current.prev;
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
