package stepik.training;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@SuppressWarnings("unused")
public class StepikThreeStarLists {

    public static int countTowers(List<List<Integer>> grid) {
        List<Integer> lastOne = grid.get(grid.size() - 1);
        return (int) lastOne.stream().filter(i -> i == 0).count();
    }

    public static String countTiles(List<List<Integer>> grid) {
        long earth = grid.stream().flatMap(list -> list.stream().filter(i -> i == 1)).count();
        long water = grid.stream().flatMap(list -> list.stream().filter(i -> i == 0)).count();
        long ship = grid.stream().flatMap(list -> list.stream().filter(i -> i == 2)).count();
        long volcano = grid.stream().flatMap(list -> list.stream().filter(i -> i == 3)).count();
        return "блоков воды: " + water
                + ", блоков земли: " + earth
                + ", блоков кораблей: " + ship
                + ", блоков вулканов: " + volcano;
    }

    public static String findPairs(int target, List<Integer> numbers) {
        List<Integer> pairs = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i) + numbers.get(j) == target) {
                    pairs.add(numbers.get(i));
                    pairs.add(numbers.get(j));
                }
            }
        }

        if (pairs.isEmpty()) {
            return "()";
        }

        return formatPairsInBrackets(pairs);
    }

    private static String formatPairsInBrackets(List<Integer> pairs) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < pairs.size() - 1; i += 2) {
            sb.append("(");
            sb.append(pairs.get(i));
            sb.append("+").append(pairs.get(i + 1)).append(")");
        }

        return sb.toString();
    }
}
