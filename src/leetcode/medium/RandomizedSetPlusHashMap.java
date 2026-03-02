package leetcode.medium;

import java.util.*;

public class RandomizedSetPlusHashMap {
    private List<Integer> values;
    private Map<Integer, Integer> indexMap;
    private Random random;

    public RandomizedSetPlusHashMap() {
        values = new ArrayList<>();
        indexMap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (indexMap.containsKey(val)) {
            return false;
        }

        indexMap.put(val, values.size());
        values.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) {
            return false;
        }

        int indexToRemove = indexMap.get(val);
        int lastElement = values.getLast();

        values.set(indexToRemove, lastElement);
        indexMap.put(lastElement, indexToRemove);

        values.removeLast();
        indexMap.remove(val);

        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(values.size());
        return values.get(randomIndex);
    }
}
