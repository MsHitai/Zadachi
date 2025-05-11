package ya.algorithm_course.fifth_sprint.final_task;

/**
 * <a href="https://contest.yandex.ru/contest/24810/run-report/138185140/">...</a>
 /**
 * -- ПРИНЦИП РАБОТЫ --
 * Удаление узла в дереве происходит по принципу присвоения нулевого (null) значения для левого, либо правого поддерева.
 * У узла есть несколько случаев - нет детей, тогда просто возвращаем null, есть левый ребенок - возвращаем сразу его,
 * также и для правого, и есть оба ребенка. В этом случае мы можем вернуть либо самый большой из левого поддерева, либо
 * самый маленький из правого - я выбрала последний вариант.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Рассмотрим дерево из 5 узлов: корень 10, левый ребенок 5, у него левый ребенок 4, правый 6. Правый от 10 - 15.
 *           10
 *       5       15
 *   4      6
 * <p>
 * Попробуем удалить узел со значением 5, запускаем метод удаления, отправляя корень дерева в метод remove и значение 5.
 * Искомое значение меньше значения у корня, поэтому идем в левое поддерево, оба поддерева у узла со значением 5 не равны
 * null, поэтому попадаем в самый последний else в методе. Находим минимальное значение в правом поддереве. Так как у
 * правого поддерева от 5 нет левого ребенка, возвращается сам узел. Переписываем значение в узле 5 на 6 и переприсваем
 * правое поддерево, вызвав метод удаления минимального значения справа. Так как первая проверка проверяет не null ли
 * левый ребенок, сразу выходим из метода, возвращая правое поддерево от бывшего узла со значением 6, т.е. null.
 * <p>
 * Итоговое дерево теперь имеет вид:
 *           10
 *       6       15
 *   4
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Алгоритм рекурсивно находит нужный узел, перемещаясь то влево, то вправо в поиске нужного значения. Сложность работы
 * такого алгоритма на BST составляет O (h), где h - высота дерева, так как дерево является корректным бинарным деревом
 * поиска из условия задачи. В случае двух поддеревьев у узла, мы ищем минимальный узел снова за O (rH), где rH - высота
 * правого поддерева. Удаление также занимает O (rH), так как сначала идет поиск нужного узла. Так как общая высота дерева
 * не превосходит h, итоговая сложность составляет O (h), что для BST можно считать O(log n).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * В этом задании не происходит выделения дополнительной памяти (за исключением tempLeft), нет дополнительного считывания
 * данных, так что можно считать, что пространственная сложность составляет O(log n).
 */
public class RemoveNode {

    public static Node remove(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.getValue()) {
            root.setLeft(remove(root.getLeft(), key));
        } else if (key > root.getValue()) {
            root.setRight(remove(root.getRight(), key));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            } else {
                Node tempLeft = findSuccessor(root.getRight());
                root.setValue(tempLeft.getValue());
                root.setRight(deleteMin(root.getRight()));
            }
        }

        return root;
    }

    private static Node findSuccessor(Node root) {
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;
    }

    private static Node deleteMin(Node root) {
        if (root.getLeft() == null) {
            return root.getRight();
        }
        root.setLeft(deleteMin(root.getLeft()));
        return root;
    }

    private static void test() {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        System.out.println(newHead.getValue() == 5);
        System.out.println(newHead.getRight() == node5);
        System.out.println(newHead.getRight().getValue() == 8);
    }

    public static void main(String[] args) {
        RemoveNode.test();
    }
}

class Node {
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
