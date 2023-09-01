package stepik.algorithms;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem {

    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] first = line.split(" ");
        int quantityOfThings = Integer.parseInt(first[0]);
        int maxWeight = Integer.parseInt(first[1]);*/

        List<Item> allItems = new ArrayList<>();

        int maxVolume = 50;

        allItems.add(new Item(60, 20));
        allItems.add(new Item(100, 50));
        allItems.add(new Item(120, 30));

        /*while (scanner.hasNext()) {
            String s = scanner.nextLine();
            String [] prices = s.split(" ");
            Item item = new Item(Integer.parseInt(prices[0]), Integer.parseInt(prices[1]));
            allItems.add(item);
        }*/

        allItems.sort((o1, o2) -> {
            long r1 = (long) (o1.value * o2.volume);
            long r2 = (long) (o2.value * o1.volume);
            return -Long.compare(r1, r2);
        });

        System.out.println(getTotalValue(allItems, maxVolume)); // can cut any amount from an item

        List<Item> toSteal = knapsack(allItems, maxVolume); // 0-1 knapsack problem
        double amount = 0;
        for (Item item : toSteal) {
            amount += item.value;
        }
        System.out.println(amount);
    }

    public static double getTotalValue(List<Item> items, int maxCapacity) {
        double result = 0;

        for (Item item : items) {
            if (item.volume <= maxCapacity) {
                result += item.value;
                maxCapacity -= item.volume;
            } else {
                result += item.value * maxCapacity / item.volume;
                break;
            }
        }

        return result;
    }

    public static List<Item> knapsack(List<Item> items, int maxCapacity) {

        double[][] table = new double[items.size() + 1][maxCapacity + 1];
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            for (int capacity = 1; capacity <= maxCapacity; capacity++) {
                double prevItemValue = table[i][capacity];
                if (capacity >= item.volume) { // item fits in knapsack
                    double valueFreeingWeightForItem = table[i][(int) (capacity - item.volume)];
                    // only take if more valuable than previous item
                    table[i + 1][capacity] = Math.max(valueFreeingWeightForItem + item.value, prevItemValue);
                } else { // no room for this item
                    table[i + 1][capacity] = prevItemValue;
                }
            }
        }

        List<Item> solution = new ArrayList<>();
        int capacity = maxCapacity;
        for (int i = items.size(); i > 0; i--) { // work backwards
            // was this item used?
            if (table[i - 1][capacity] != table[i][capacity]) {
                solution.add(items.get(i - 1));
                capacity -= items.get(i - 1).volume;
            }
        }
        return solution;
    }

    private static class Item {
        private final double volume;
        private final double value;

        public Item(double value, double volume) {
            this.value = value;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "volume=" + volume +
                    ", value=" + value +
                    '}';
        }
    }
}
