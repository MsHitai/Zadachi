package stepik.training;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@SuppressWarnings("unused")
public class StepikThreeStarLists {

    private static final Map<Integer, String> VOLCANO_NEIGHBORS_DICTIONARY = Map.of(
            0, "водой", 1, "землей", 2, "кораблем"
    );

    public static int countTowers(List<List<Integer>> grid) {
        List<Integer> lastOne = grid.get(grid.size() - 1);
        return (int) lastOne.stream().filter(i -> i == 0).count();
    }

    public static String analyzeVolcano(List<List<Integer>> grid) {
        List<Volcano> volcanoes = new ArrayList<>();

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j) == 3) {
                    Volcano volcano = createVolcano(grid, j, i);
                    volcanoes.add(volcano);
                }
            }
        }

        return getVolcanoResult(volcanoes);
    }

    private static Volcano createVolcano(List<List<Integer>> grid, int listIndex, int gridIndex) {
        Volcano volcano = new Volcano();
        if (listIndex > 0) {
            volcano.setWest(grid.get(gridIndex).get(listIndex - 1));
        }
        if (listIndex + 1 < grid.get(gridIndex).size()) {
            volcano.setEast(grid.get(gridIndex).get(listIndex + 1));
        }
        if (gridIndex > 0) {
            volcano.setNorth(grid.get(gridIndex - 1).get(listIndex));
        }
        if (gridIndex + 1 < grid.size()) {
            volcano.setSouth(grid.get(gridIndex + 1).get(listIndex));
        }
        return volcano;
    }

    private static String getVolcanoResult(List<Volcano> volcanoes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < volcanoes.size(); i++) {
            sb.append("Вулкан ").append(i + 1).append(" граничит на ");
            Volcano volcano = volcanoes.get(i);
            String north = VOLCANO_NEIGHBORS_DICTIONARY.get(volcano.getNorth());
            if (north != null) {
                sb.append("севере с ").append(north).append(", на ");
            }
            String east = VOLCANO_NEIGHBORS_DICTIONARY.get(volcano.getEast());
            if (east != null) {
                sb.append("востоке с ").append(east).append(", на ");
            }
            String south = VOLCANO_NEIGHBORS_DICTIONARY.get(volcano.getSouth());
            if (south != null) {
                sb.append("юге с ").append(south).append(", на ");
            }
            String west = VOLCANO_NEIGHBORS_DICTIONARY.get(volcano.getWest());
            if (west != null) {
                sb.append("западе с ").append(west).append(". ");
            }
            String temp = sb.toString();
            if (!temp.endsWith(". ")) {
                sb = new StringBuilder(temp.substring(0, temp.length() - 5)).append(". ");
            }
        }

        return sb.toString();
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
