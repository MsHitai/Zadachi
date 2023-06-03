package fifth.sprint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PotatoLaboratory {

    public static void main(String[] args) {
        List<Potato> potatoes = List.of(
                new Potato(1, 30, 30, 30),
                new Potato(2, 35, 31, 35),
                new Potato(3, 40, 35, 44),
                new Potato(4, 28, 44, 41),
                new Potato(5, 33, 23, 30),
                new Potato(6, 35, 33, 33),
                new Potato(7, 38, 41, 24)
        );

        List<Potato> fourUnderExperiment = findPotatoesForExperiment(potatoes);

        System.out.println("Картофелины для эксперимента: " + fourUnderExperiment);
    }

    private static List<Potato> findPotatoesForExperiment(List<Potato> potatoes) {
				/* Вычислите две самые большие и две самые маленькие картофелины, 
           а затем выведите их в порядке от самых маленьких до самых больших.*/
        List<Potato> neededPotatoes = new ArrayList<>(potatoes);
        Collections.sort(neededPotatoes);

        return List.of(neededPotatoes.get(0), neededPotatoes.get(1), neededPotatoes.get(neededPotatoes.size() - 2),
                neededPotatoes.get(neededPotatoes.size() - 1));
    }
}