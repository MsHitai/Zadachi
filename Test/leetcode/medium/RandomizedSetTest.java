package leetcode.medium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

    @Test
    void testComplexOperationSequence() {
        String[] commands = {"RandomizedSet", "insert", "insert", "remove", "remove", "insert", "insert", "remove", "insert", "remove", "insert", "getRandom", "getRandom", "getRandom", "insert", "getRandom", "getRandom", "remove", "getRandom", "remove", "insert", "getRandom", "insert", "getRandom", "getRandom", "insert", "remove", "getRandom", "insert", "insert", "getRandom", "insert", "remove", "insert", "getRandom", "insert", "insert", "insert", "insert", "remove", "getRandom", "getRandom", "insert", "insert", "getRandom", "getRandom", "insert", "remove", "insert", "insert", "remove", "remove", "getRandom", "insert", "insert", "insert", "remove", "getRandom", "remove", "insert", "getRandom", "insert", "insert", "remove", "remove", "getRandom", "insert", "getRandom", "remove", "insert", "getRandom", "getRandom", "insert", "insert", "insert", "insert", "remove", "remove", "insert", "insert", "getRandom", "getRandom", "insert", "insert", "insert", "remove", "remove", "remove", "remove", "insert", "remove", "remove", "getRandom", "insert", "getRandom", "insert", "getRandom", "getRandom", "insert", "remove", "getRandom", "insert", "remove", "remove", "getRandom", "getRandom", "getRandom", "insert", "getRandom", "insert", "insert", "insert", "getRandom", "getRandom", "insert", "remove", "remove", "insert", "getRandom", "insert", "getRandom", "remove", "getRandom", "insert", "insert", "insert", "insert", "remove", "insert", "getRandom", "getRandom", "getRandom", "getRandom", "insert", "insert", "getRandom", "getRandom", "remove", "remove", "remove", "getRandom", "getRandom", "insert", "getRandom", "insert", "remove", "insert", "getRandom", "insert", "insert", "insert", "getRandom", "insert", "getRandom", "getRandom", "remove", "insert", "getRandom", "insert", "remove", "remove", "remove", "remove", "remove", "insert", "remove", "remove", "remove", "getRandom", "insert", "insert", "getRandom", "insert", "getRandom", "remove", "remove", "insert", "getRandom", "remove", "getRandom", "insert", "insert", "remove", "remove", "remove", "remove", "remove", "remove", "remove", "getRandom", "getRandom", "remove", "remove", "getRandom", "remove", "insert", "remove", "remove", "getRandom", "insert", "insert", "remove", "insert", "remove", "remove", "insert", "remove", "insert", "remove", "getRandom", "insert", "remove", "remove", "insert", "insert", "insert", "insert", "insert", "insert", "insert", "getRandom", "remove", "getRandom", "insert", "getRandom", "remove", "insert", "insert", "remove", "remove", "getRandom", "remove", "remove", "getRandom", "getRandom", "insert", "insert", "getRandom", "getRandom", "insert", "getRandom", "insert", "remove", "getRandom", "insert", "insert", "remove", "insert", "insert", "getRandom", "remove", "insert", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom", "insert", "remove", "getRandom", "insert", "getRandom", "insert", "getRandom", "insert", "remove", "insert", "insert", "insert", "insert", "remove", "insert", "insert", "getRandom", "insert", "getRandom", "getRandom", "remove", "insert", "getRandom", "getRandom", "getRandom", "insert", "insert", "getRandom", "getRandom", "insert", "insert", "getRandom", "getRandom", "remove", "getRandom", "insert", "insert", "remove", "getRandom", "remove", "getRandom", "remove", "getRandom", "insert", "getRandom", "insert", "getRandom", "remove", "remove", "getRandom"};
        Integer[][] values = {{}, {-20}, {-47}, {-20}, {-47}, {-119}, {-119}, {-119}, {-99}, {-99}, {-121}, {}, {}, {},
                {144}, {}, {}, {-121}, {}, {144}, {154}, {}, {-13}, {}, {}, {16}, {16}, {}, {-78}, {44}, {}, {57}, {154},
                {-25}, {}, {142}, {142}, {-84}, {-84}, {-78}, {}, {}, {-115}, {110}, {}, {}, {26}, {-13}, {-122}, {-14},
                {26}, {-115}, {}, {-4}, {-102}, {-35}, {44}, {}, {-84}, {153}, {}, {-28}, {-69}, {-122}, {-4}, {}, {138},
                {}, {-102}, {76}, {}, {}, {133}, {115}, {31}, {-59}, {138}, {-59}, {147}, {109}, {}, {}, {84}, {-35}, {-113},
                {110}, {147}, {-25}, {109}, {66}, {133}, {84}, {}, {-71}, {}, {-19}, {}, {}, {-138}, {-138}, {}, {80}, {-71},
                {31}, {}, {}, {}, {-31}, {}, {104}, {104}, {142}, {}, {}, {55}, {-35}, {-69}, {-92}, {}, {-91}, {}, {55}, {},
                {-59}, {104}, {126}, {14}, {-91}, {60}, {}, {}, {}, {}, {135}, {57}, {}, {}, {60}, {60}, {-92}, {}, {}, {-127},
                {}, {-113}, {-14}, {-77}, {}, {79}, {-20}, {25}, {}, {100}, {}, {}, {126}, {-93}, {}, {128}, {-59}, {14}, {57},
                {80}, {128}, {-60}, {-60}, {-28}, {-19}, {}, {-131}, {86}, {}, {-69}, {}, {-77}, {-77}, {11}, {}, {-31}, {}, {90},
                {-20}, {76}, {-20}, {-20}, {-93}, {153}, {25}, {115}, {}, {}, {-127}, {104}, {}, {86}, {-95}, {-131}, {-131}, {},
                {47}, {112}, {90}, {-105}, {-69}, {-69}, {28}, {-95}, {67}, {142}, {}, {118}, {-105}, {118}, {149}, {-113}, {-8},
                {150}, {150}, {0}, {0}, {}, {11}, {}, {35}, {}, {0}, {76}, {128}, {-113}, {-113}, {}, {66}, {28}, {}, {}, {111},
                {111}, {}, {}, {50}, {}, {-76}, {112}, {}, {46}, {157}, {150}, {-36}, {-123}, {}, {149}, {134}, {}, {}, {}, {}, {},
                {48}, {128}, {}, {-135}, {}, {-133}, {}, {-127}, {-36}, {97}, {97}, {38}, {38}, {-127}, {150}, {75}, {}, {-75}, {},
                {}, {111}, {63}, {}, {}, {}, {-107}, {-107}, {}, {}, {-42}, {127}, {}, {}, {-133}, {}, {62}, {106}, {135}, {}, {79},
                {}, {35}, {}, {-32}, {}, {-47}, {}, {97}, {-47}, {}, {-32}, {-31}, {}, {75}, {-118}, {-107}, {}, {}, {152}};
        Object[] expected = {null, true, true, true, true, true, false, true, true, true, true, -121, -121, -121, true, -121, -121, true,
                144, true, true, 154, true, 154, 154, true, true, 154, true, true, -78, true, true, false, 44, false, false, true, false, true,
                -84, 44, true, true, 110, 110, true, true, false, false, true, true, 110, true, true, true, true, 57, true, false, -35, true, true,
                false, true, -35, true, -102, true, true, 138, -28, false, true, true, true, true, true, false, true, 76, 31, true, false, true,
                true, true, true, true, true, true, true, 76, true, 76, true, 76, -19, true, true, -19, true, true, true, 76, -28, 76, true, -19,
                true, false, true, -31, 80, true, true, false, false, -19, true, -28, true, 142, false, false, true, false, true, false, -19,
                -19, -28, -19, true, true, -113, 76, true, false, false, 76, 135, false, -31, false, true, true, -19, true, true, false, 80,
                true, -31, 76, true, true, 104, true, false, false, true, true, true, true, true, false, true, 66, true, true, 104, true, 79,
                true, false, true, -69, true, 79, true, false, true, true, false, true, false, false, false, 100, 79, false, true, 90, true,
                true, true, false, -69, false, true, true, true, true, false, true, true, true, false, -113, true, true, true, true, false,
                true, true, false, true, false, 66, true, 0, false, 79, true, true, true, true, false, 135, true, true, 76, 67, false, false,
                135, 79, true, 128, false, true, 79, true, true, true, false, true, -8, true, true, 67, 79, 50, 128, -123, true, true, 135,
                false, -8, true, 67, true, true, true, false, false, false, true, true, true, 79, false, 75, 50, true, true, 75, -133, 50,
                false, false, 50, 50, true, true, 63, -8, true, -8, true, false, false, -8, false, 63, true, -8, true, 46, true, 63, false,
                true, -42, true, false, 97, false, false, true, 127, 46, true};
        Object[] actual = new Object[expected.length];

        RandomizedSet set = new RandomizedSet();

        for (int i = 0; i < commands.length; i++) {
            String cmd = commands[i];
            Object result = switch (cmd) {
                case "RandomizedSet" -> {
                    set = new RandomizedSet();
                    yield null;
                }
                case "insert" -> set.insert(values[i][0]);
                case "remove" -> set.remove(values[i][0]);
                case "getRandom" -> set.getRandom();
                default -> null;
            };
            actual[i] = result;
        }
        assertTrue(Arrays.deepEquals(expected, actual));
    }
}
