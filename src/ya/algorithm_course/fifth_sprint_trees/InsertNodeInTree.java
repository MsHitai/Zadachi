package ya.algorithm_course.fifth_sprint_trees;

public class InsertNodeInTree {

    public static Node insert(Node root, int key) {
        if (root == null) {
            return new Node(null, null, key);
        }

        if (key < root.getValue()) {
            root.setLeft(insert(root.getLeft(), key));
        } else {
            root.setRight(insert(root.getRight(), key));
        }

        return root;
    }

    // <template>
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
    // <template>

    private static void test() {
        Node node1 = new Node(null, null, 7);
        Node node2 = new Node(node1, null, 8);
        Node node3 = new Node(null, node2, 7);
        Node newHead = insert(node3, 6);
        System.out.println(newHead == node3);
        System.out.println(newHead.getLeft().getValue() == 6);
    }

    public static void main(String[] args) {
        InsertNodeInTree.test();
    }
}
