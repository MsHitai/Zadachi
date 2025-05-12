package ya.algorithm_course.fifth_sprint_trees;

import java.util.ArrayDeque;
import java.util.Queue;

public class LampsTree {

    public static int treeSolution(Node head) {
        int count = -1;
        Queue<Node> queue = new ArrayDeque<>();
        if (head != null) {
            queue.add(head);
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node != null && node.value > count) {
                count = node.value;
            }
            if (node != null && node.left != null) {
                queue.add(node.left);
            }
            if (node != null && node.right != null) {
                queue.add(node.right);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        LampsTree.test();
        //LampsTree.treeSolution()
    }

    // <template>
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    // <template>


    public static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        System.out.println(treeSolution(node4) == 3);
        System.out.println(treeSolution(node4));
    }
}
