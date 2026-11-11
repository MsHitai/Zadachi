package leetcode.medium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RandomizedSetTest {

    private RandomizedSet randomizedSet;

    @BeforeEach
    void setUp() {
        randomizedSet = new RandomizedSet();
    }

    @Test
    void testInsertReturnsTrueForNewElement() {
        boolean result = randomizedSet.insert(1);
        assertTrue(result);
    }

    @Test
    void testRemoveReturnsFalseForNonExistentElement() {
        boolean result = randomizedSet.remove(2);
        assertFalse(result);
    }

    @Test
    void testInsertMultipleElementsAndGetRandom() {
        randomizedSet.insert(1);
        randomizedSet.insert(2);
        int randomValue = randomizedSet.getRandom();
        assertThat(randomValue).isIn(1, 2);
    }

    @Test
    void testRemoveExistingElement() {
        randomizedSet.insert(1);
        boolean result = randomizedSet.remove(1);
        assertTrue(result);
    }

    @Test
    void testInsertReturnsFalseForDuplicateElement() {
        randomizedSet.insert(2);
        boolean result = randomizedSet.insert(2);
        assertFalse(result);
    }

    @Test
    void testGetRandomWithSingleElement() {
        randomizedSet.insert(2);
        int randomValue = randomizedSet.getRandom();
        assertThat(randomValue).isEqualTo(2);
    }

    @Test
    void testRemoveAfterInsertAndGetRandom() {
        randomizedSet.insert(1);
        randomizedSet.insert(2);
        randomizedSet.remove(1);
        int randomValue = randomizedSet.getRandom();
        assertThat(randomValue).isEqualTo(2);
    }

    /**
     * Случайная последовательность операций сверяется с эталонным HashSet.
     * (Раньше здесь был скопированный прогон с LeetCode, но массивы команд и ответов в нём разъехались,
     * а конкретные значения getRandom всё равно случайны и сравнивать их нельзя.)
     */
    @Test
    void testRandomOperationsMatchHashSet() {
        Random random = new Random(42);
        Set<Integer> reference = new HashSet<>();

        for (int step = 0; step < 20_000; step++) {
            int value = random.nextInt(100) - 50;
            switch (random.nextInt(3)) {
                case 0 -> assertEquals(reference.add(value), randomizedSet.insert(value), "insert " + value);
                case 1 -> assertEquals(reference.remove(value), randomizedSet.remove(value), "remove " + value);
                default -> {
                    if (!reference.isEmpty()) {
                        assertThat(reference).contains(randomizedSet.getRandom());
                    }
                }
            }
        }
    }

    @Test
    void testGetRandomIsRoughlyUniform() {
        randomizedSet.insert(1);
        randomizedSet.insert(2);
        randomizedSet.insert(3);
        randomizedSet.remove(2);
        randomizedSet.insert(4);

        Map<Integer, Integer> counts = new HashMap<>();
        int draws = 30_000;
        for (int i = 0; i < draws; i++) {
            counts.merge(randomizedSet.getRandom(), 1, Integer::sum);
        }

        assertThat(counts.keySet()).containsExactlyInAnyOrder(1, 3, 4);
        counts.values().forEach(count -> assertThat(count).isBetween(draws / 3 - 1_000, draws / 3 + 1_000));
    }
}
