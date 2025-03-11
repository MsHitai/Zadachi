package ya.algorithm_course.first_sprint;

public class TestTheory {

    public static void main(String[] args) {
        int[] visitors = {0, 2, 3, 2, 0, 4, 1, 1, 2};
        int[] entriesByVisitor = new int[5];
        int bestVisitor = 0;

        for (int visitor : visitors) {
            entriesByVisitor[visitor]++;
            if (entriesByVisitor[visitor] > entriesByVisitor[bestVisitor]) {
                bestVisitor = visitor;
            }
        }

        System.out.println(bestVisitor);
    }
}
