package ya.algorithm_course.fifth_sprint;

import java.util.*;

public class SearchTree {

    public static boolean treeSolution(Node head) {
        return check(head, null, null);
    }

    public static boolean check(Node node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        if ((min != null && node.value <= min) || (max != null && node.value >= max)) {
            return false;
        }
        return check(node.left, min, node.value) && check(node.right, node.value, max);
    }


    // <template>
    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    // <template>


    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        System.out.println(treeSolution(node5));
        node2.value = 5;
        System.out.println(!treeSolution(node5));
    }

    public static void main(String[] args) {
        test();
        /*try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int nodeSize = Integer.parseInt(reader.readLine());
            List<String> nodes = new ArrayList<>();
            for (int i = 0; i < nodeSize; i++) {
                nodes.add(reader.readLine());
            }
            Node node = parseToNodes(nodes);
            System.out.println(SearchTree.treeSolution(node));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }*/
    }

    private static Node parseToNodes(List<String> nodes) {
        Map<Integer, NodeRelation> idToNodeRelations = new LinkedHashMap<>();
        Map<Integer, Integer> idToVal = new HashMap<>();
        int firstVal = 0;
        for (int i = 0; i < nodes.size(); i++) {
            String node = nodes.get(i);
            String[] ids = node.split(" ");
            Integer id = !ids[0].equals("None") ? Integer.parseInt(ids[0]) : null;
            int value = Integer.parseInt(ids[1]);
            Integer leftId = !ids[2].equals("None") ? Integer.parseInt(ids[2]) : null;
            Integer rightId = !ids[3].equals("None") ? Integer.parseInt(ids[3]) : null;
            idToNodeRelations.put(id, new NodeRelation(id, leftId, rightId, value));
            idToVal.put(id, value);
            if (i == 0) {
                firstVal = value;
            }
        }
        Node node = new Node(firstVal);
        for (NodeRelation relation : idToNodeRelations.values()) {
            if (relation.leftId != null) {
                int value = idToVal.get(relation.leftId);
                node.left = new Node(value);
            }
            if (relation.rightId != null) {
                int value = idToVal.get(relation.rightId);
                node.right = new Node(value);
            }
        }
        return node;
    }

    public static class NodeRelation {
        Integer id;
        Integer leftId;
        Integer rightId;
        int value;

        public NodeRelation(Integer id, Integer leftId, Integer rightId, int value) {
            this.id = id;
            this.leftId = leftId;
            this.rightId = rightId;
            this.value = value;
        }
    }
}
