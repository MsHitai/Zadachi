package ya.algorithm_course.second_sprint_recursion;

public class NodeListSolutions {
    public static void main(String[] args) {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        int idx = Solution.solution(node0, "node4");
        System.out.println(idx);
    }
}

// <template>
class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
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

class Solution {

    public static int solution(Node<String> head, String elem) {
        // Your code
        // ヽ(´▽`)/
        Node<String> node = head;
        int count = 0;
        while (node != null) {
            if (node.value.equalsIgnoreCase(elem)) {
                return count;
            }
            node = node.next;
            count++;
        }
        return -1;
    }

    public static Node<String> solution(Node<String> head, int idx) {
        // Your code
        // ヽ(´▽`)/
        int begin = 0;
        if (idx == begin) {
            head = head.next;
            return head;
        }
        Node<String> previous = head;
        Node<String> headTemp = head;
        while (idx != begin) {
            previous = headTemp;
            headTemp = headTemp.next;
            begin++;
        }

        previous.next = previous.next.next;
        return head;
    }

    public static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        Node<String> newHead = solution(node0, 1);
        assert newHead == node0;
        assert newHead.next == node2;
        assert newHead.next.next == node3;
        assert newHead.next.next.next == null;
        // result is : node0 -> node2 -> node3
    }
}
