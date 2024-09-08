package stepik.training;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class StepikTwoStarLists {

    public static int[][] createTriangle(int height) {
        int size = height + height - 1;
        int half = size / 2;
        int j = 0;
        int[][] res = new int[height][size];
        for (int i = 0; i < res.length; i++) {
            res[i][half + j] = 1;
            res[i][half - j] = 1;
            if (i + 1 < res.length) {
                res[i + 1] = Arrays.copyOf(res[i], res[i].length);
            }
            j++;
        }
        return res;
    }

    public static int[][] createSquare(int height) {
        int[][] res = new int[height][height];
        for (int[] re : res) {
            Arrays.fill(re, 1);
        }
        return res;
    }

    public static int sumOfMissingNumbers(List<Number> data) {
        List<Long> given = new ArrayList<>();
        for (Number number : data) {
            given.add(Math.round((Double) number));
        }
        Collections.sort(given);
        List<Long> result = new ArrayList<>();
        long temp;
        for (int i = 1; i < given.size(); i++) {
            if (given.get(i) != given.get(i - 1) + 1) {
                temp = given.get(i) - given.get(i - 1);
                if (temp != 1) {
                    for (long j = 1; j < temp; j++) {
                        if (given.get(i - 1) + j < given.get(i)) {
                            result.add(given.get(i - 1) + j);
                        }
                    }
                } else {
                    result.add(given.get(i - 1) + 1);
                }
            }
        }
        int sum = 0;
        for (Long i : result) {
            sum += i;
        }
        return sum;
    }

    public static int sumOfMissingEvenNumbers(List<Integer> data) {
        int s = IntStream.range(data.get(0), data.get(data.size() - 1) + 1).filter(n -> n % 2 == 0).sum();
        return s - data.stream().mapToInt(Integer::intValue).filter(n -> n % 2 == 0).sum();
    }

    public static int sumOfMissingOddNumbers(List<Integer> data) {
        int s = IntStream.range(data.get(0), data.get(data.size() - 1) + 1).filter(n -> n % 2 != 0).sum();
        return s - data.stream().mapToInt(Integer::intValue).filter(n -> n % 2 != 0).sum();
    }

    public static String sumOfMissPairs(List<Integer> data) {
        if (data.isEmpty()) {
            return "()";
        }
        int j = 1;
        boolean even = data.size() % 2 == 0;
        int halfIndex = data.size() / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if (!even && i == halfIndex) {
                sb.append("(")
                        .append(data.get(i))
                        .append(",")
                        .append(data.get(i))
                        .append(")");
                break;
            } else if (even && i == halfIndex - 1) {
                fillNumbers(data, sb, i, j);
                break;
            }
            fillNumbers(data, sb, i, j);
            if (i != data.size() - 1) {
                sb.append(",");
            }
            j++;
        }

        return sb.toString();
    }

    private static void fillNumbers(List<Integer> data, StringBuilder sb, int i, int j) {
        sb.append("(")
                .append(data.get(i))
                .append(",")
                .append(data.get(data.size() - j))
                .append(")");
    }

    public static boolean isSorted(List<Integer> data) {
        List<Integer> copy = new ArrayList<>(data);
        Collections.sort(copy);
        return data.equals(copy);
    }

    public static List<List<String>> advancedSort(List<String> data) {
        Map<String, Integer> copies = new LinkedHashMap<>();
        for (String word : data) {
            copies.put(word, copies.getOrDefault(word, 0) + 1);
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : copies.entrySet()) {
            String word = entry.getKey();
            int repeated = entry.getValue();
            List<String> subArray = new ArrayList<>();
            for (int i = 0; i < repeated; i++) {
                subArray.add(word);
            }
            result.add(subArray);
        }
        return result;
    }

    public static List<String> badCustomSort(List<String> data, String order) {
        List<Character> temp = new ArrayList<>();
        List<String> copy = new ArrayList<>();
        String str;
        switch (order) {
            case "ASC" -> {
                for (String s : data) {
                    for (int i = 0; i < s.length(); i++) {
                        temp.add(s.charAt(i));
                    }
                    str = temp.stream().map(String::valueOf)
                            .sorted()
                            .collect(Collectors.joining(""));
                    temp.clear();
                    copy.add(str);
                }
                return copy;
            }
            case "DESC" -> {
                for (String s : data) {
                    for (int i = 0; i < s.length(); i++) {
                        temp.add(s.charAt(i));
                    }
                    str = temp.stream().map(String::valueOf)
                            .sorted(Comparator.comparing(String::valueOf).reversed())
                            .collect(Collectors.joining(""));
                    temp.clear();
                    copy.add(str);
                }
                return copy;
            }
        }
        return null;
    }

    public static List<String> betterCustomSort(List<String> data, String order) {
        return data.stream()
                .map(it -> Stream.of(it.split(""))
                        .sorted(order.equals("ASC") ? Comparator.naturalOrder() : Comparator.reverseOrder())
                        .collect(Collectors.joining()))
                .toList();
    }

    public static List<Integer> customSort(List<Integer> data, String order) {
        return data.stream()
                .map(String::valueOf)
                .map(s -> Stream.of(s.split(""))
                        .sorted(order.equals("ASC") ? Comparator.naturalOrder() : Comparator.reverseOrder())
                        .collect(Collectors.joining()))
                .map(Integer::parseInt)
                .toList();
    }
}
