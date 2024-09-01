package stepik.training;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Component
public class StepikOneStarTaskSolver {

    public static final int MAX = 100;
    public static final String POTION = "Зелье";
    public static final int INCREASE = 10;
    public static final String WRONG_SIZE = "Неверный размер";
    public static final String RIGHT = "right";
    public static final String LEFT = "left";
    private static final String WRONG_POSITION = "Неверная позиция";

    public static String fillArray(int size, String position, List<Integer> data) {
        if (!RIGHT.equals(position) && !LEFT.equals(position)) {
            return WRONG_POSITION;
        } else if (data.size() > size) {
            return WRONG_SIZE;
        } else if (data.size() == size) {
            return getResultString(data);
        } else {
            insertLeft(position, size, data);
            insertRight(position, size, data);
            return getResultString(data);
        }
    }

    private static String getResultString(List<Integer> data) {
        return data.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    private static void insertRight(String position, int size, List<Integer> data) {
        if (!RIGHT.equals(position)) {
            return;
        }
        for (int i = data.size(); i < size; i++) {
            data.add(0);
        }
    }

    private static void insertLeft(String position, int size, List<Integer> data) {
        if (!LEFT.equals(position)) {
            return;
        }
        int limit = size - data.size();
        for (int i = 0; i < limit; i++) {
            data.add(i, 0);
        }
    }

    public static int findSumWithin(List<Integer> data, int start, int end) {
        int indexStart = data.indexOf(start);
        int indexEnd = data.lastIndexOf(end);
        int sum = 0;
        if (indexStart == -1 || indexEnd == -1) {
            return sum;
        }
        for (int i = indexStart; i <= indexEnd; i++) {
            sum += data.get(i);
        }
        return sum;
    }

    public static Integer findSideSum(List<List<Integer>> grid) {
        if (grid.isEmpty()) {
            return -1;
        }
        int sum = 0;
        int first = 0;
        for (int i = 0; i < grid.size(); i++) {
            List<Integer> subArray = grid.get(i);
            if (i == 0 || i == grid.size() - 1) {
                for (Integer integer : subArray) {
                    sum += integer;
                }
            }
            sum += subArray.get(first) + subArray.get(subArray.size() - 1);
        }
        return sum;
    }

    public static Integer findDiagonalSum(List<List<Integer>> grid) {
        if (!validSize(grid)) {
            return -1;
        }
        int sum = 0;
        int j = 0;

        for (List<Integer> list : grid) {
            sum += list.get(j++);

        }

        for (List<Integer> list : grid) {
            sum += list.get(--j);

        }
        return sum;
    }

    private static boolean validSize(List<List<Integer>> grid) {
        if (grid.isEmpty()) {
            return false;
        }
        int horizontal = grid.get(0).size();
        int countVertical = 0;
        for (List<Integer> list : grid) {
            countVertical++;
        }
        return horizontal == countVertical;
    }

    public static List<Integer> mirrorArray(List<Integer> data) {
        List<Integer> result = new ArrayList<>(data);
        data.remove(data.size() - 1);
        Collections.reverse(data);
        result.addAll(data);
        return result;
    }

    public static String convertToString(List<Integer> arr) {
        return arr.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    public static List<Integer> replaceNegativeWithZero(List<Integer> data) {
        List<Integer> result = new ArrayList<>(data);
        for (Integer i : data) {
            if (i < 0) {
                result.add(data.indexOf(i), 0);
                result.remove(i);
            }
        }
        return result;
    }

    public static boolean hasTwoConsecutiveZeros(List<Integer> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            if (data.get(i) == 0 && data.get(i + 1) == 0) {
                return true;
            }
        }
        return false;
    }

    public static List<Integer> replaceZeroWithSum(List<Integer> data) {
        List<Integer> result = new ArrayList<>(data);
        for (int i = 2; i < data.size(); i++) {
            if (data.get(i) == 0) {
                int sum = result.get(i - 2) + result.get(i - 1);
                result.set(i, sum);
            }
        }
        return result;
    }

    public static String countSymbols(List<String> data) {
        if (data.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String lastValue = data.get(0);
        int count = 0;
        for (String value : data) {
            if (value.equals(lastValue)) {
                count++;
            } else {
                sb.append(lastValue).append(count);
                lastValue = value;
                count = 1;
            }
        }
        sb.append(lastValue).append(count);
        return sb.toString();
    }

    public static String mapNumberToValue(String message) {
        StringJoiner sj = new StringJoiner(", ");
        for (int i = 0; i < message.length() - 1; i += 2) {
            String letter = String.valueOf(message.charAt(i));
            int count = Integer.parseInt(String.valueOf(message.charAt(i + 1)));
            repeatString(count, sj, letter);
        }

        return sj.toString();
    }

    private static void repeatString(int limit, StringJoiner joiner, String value) {
        for (int i = 0; i < limit; i++) {
            joiner.add(value);
        }
    }

    public static List<Integer> deleteZeroValues(List<Integer> data) {
        return data.stream().filter(v -> v != 0).collect(Collectors.toList());
    }

    public static boolean findNumber(int k, List<List<Integer>> data) {
        return data.stream().flatMap(List::stream).anyMatch(v -> v == k);
    }

    public static int increasePower(int power, List<String> items) {
        long multiplier = items.stream().filter(v -> v.equalsIgnoreCase(POTION)).count();
        power += (INCREASE * (int) multiplier);
        return Math.min(power, MAX);
    }

    public static boolean allFalse(List<Boolean> data) {
        return data.stream().allMatch(Boolean.FALSE::equals);
    }

    public static String copyItems(int n, List<Integer> items) {
        List<String> copies = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            copies.add(String.valueOf(items.get(i)));
        }
        List<String> itemStrings = items.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
        itemStrings.addAll(copies);
        return String.join(", ", itemStrings);
    }

    public static int increasePowerByCaffeine(int power, List<String> items) {
        int energyMultiplier = Collections.frequency(items, "Энергетик");
        int energyCost = 5;
        int coffeeMultiplier = Collections.frequency(items, "Кофе");
        int coffeeCost = 10;
        power += (energyCost * energyMultiplier) + (coffeeCost * coffeeMultiplier);
        return Math.min(power, MAX);
    }

    public static int findMinNumber(List<List<Integer>> data) {
        return data.stream()
                .flatMap(List::stream)
                .min(Integer::compareTo)
                .orElse(-1);
    }

    public static int findMaxNumber(List<List<Integer>> data) {
        return data.stream()
                .flatMap(List::stream)
                .max(Integer::compareTo)
                .orElse(-1);
    }

    public static String multiplyAndSortList(List<Integer> data, int n) {
        StringBuilder sb = new StringBuilder();
        data.stream()
                .sorted()
                .forEach(number -> sb.append(number * n).append(", "));
        return sb.substring(0, sb.length() - 2);
    }

    public static String moveItems(int n, List<Integer> items) {
        StringBuilder sb = new StringBuilder();
        for (int i = n; i < items.size(); i++) {
            sb.append(items.get(i)).append(", ");
        }
        for (int i = 0; i < n; i++) {
            sb.append(items.get(i));
            if (i != n - 1) {
                sb.append(", ");
            }
        }
        Collections.rotate(items, -n);
        return sb.toString();
    }

    public static String findEvenOddNumbers(List<Integer> data) {
        StringBuilder result = new StringBuilder();
        List<Integer> even = new ArrayList<>();
        List<Integer> uneven = new ArrayList<>();
        data.stream()
                .sorted()
                .forEach(n -> {
                    if (n % 2 == 0) {
                        even.add(n);
                    } else {
                        uneven.add(n);
                    }
                });
        iterateThroughList(even, result);
        iterateThroughList(uneven, result);

        return result.toString();
    }

    private static void iterateThroughList(List<Integer> numbers, StringBuilder result) {
        for (int i = 0; i < numbers.size(); i++) {
            if (i == 0 && i + 1 == numbers.size()) {
                result.append("(")
                        .append(numbers.get(i))
                        .append(") ");
                continue;
            }
            if (i == 0) {
                result.append("(").append(numbers.get(i)).append(", ");
                continue;
            }
            if (i == numbers.size() - 1) {
                result.append(numbers.get(i)).append(") ");
                continue;
            }
            result.append(numbers.get(i)).append(", ");
        }
    }
}