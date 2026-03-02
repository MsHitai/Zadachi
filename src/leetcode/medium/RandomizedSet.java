package leetcode.medium;

import java.util.Arrays;
import java.util.Random;

public class RandomizedSet {

    private Integer[] set;
    private final Random random;
    private int actualSize;

    public RandomizedSet() {
        this.actualSize = 0;
        this.set = new Integer[16];
        random = new Random();
    }

    public boolean insert(int val) {
        if (this.set.length == this.actualSize) {
            this.set = Arrays.copyOf(this.set, actualSize * 2);
        }
        int index = getIndex(val);
        if (set[index] != null) {
            return false;
        }
        set[index] = val;
        this.actualSize++;
        return true;
    }

    public boolean remove(int val) {
        int index = getIndex(val);
        if (set[index] == null) {
            return false;
        }
        set[index] = null;
        this.actualSize--;
        return true;
    }

    public int getRandom() {
        int index = random.nextInt(0, set.length);
        while (set[index] == null) {
            index = random.nextInt(0, set.length);
            if (set[index] != null) {
                return set[index];
            }
        }
        return set[index];
    }

    private long getHash(int value) {
        if (value == 0) {
            return 0;
        }
        return Math.abs(17L * value);
    }

    private int getIndex(int key) {
        long hash = getHash(key);
        return (int) (hash % this.set.length);
    }
}
