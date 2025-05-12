package ya.algorithm_course.fifth_sprint_trees;

public class IsBalancedTree {

    public static boolean treeSolution(Node head) {
        return countHeight(head) != -1;
    }

    private static int countHeight(Node node) {
        if (node == null) {
            return 0;
        }

        int leftCount = countHeight(node.left);
        if (leftCount == -1) {
            return -1;
        }
        int rightCount = countHeight(node.right);
        if (rightCount == -1) {
            return -1;
        }
        if (Math.abs(leftCount - rightCount) > 1) {
            return -1;
        }

        return Math.max(leftCount, rightCount) + 1;
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

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        System.out.println(treeSolution(node5));
    }

    public static void main(String[] args) {
        IsBalancedTree.test();
    }
}
