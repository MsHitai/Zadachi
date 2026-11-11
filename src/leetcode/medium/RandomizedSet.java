package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * LeetCode 380 "Insert Delete GetRandom O(1)".
 * <p>
 * Список значений даёт getRandom за O(1) (случайный индекс), а мапа "значение -> индекс в списке" даёт
 * insert и remove за O(1). Чтобы удалять из списка за O(1), удаляемый элемент меняем местами с последним
 * и удаляем хвост.
 * <p>
 * Почему не подошла первая версия (массив с индексом по хешу): разные значения с одинаковым индексом
 * (например 0 и 16 при длине 16) затирали друг друга, после расширения массива элементы оставались на старых
 * индексах и переставали находиться, а getRandom перебирал пустые ячейки и на пустом наборе зависал.
 */
public class RandomizedSet {

    private final List<Integer> values = new ArrayList<>();
    private final Map<Integer, Integer> indexByValue = new HashMap<>();
    private final Random random = new Random();

    public boolean insert(int val) {
        if (indexByValue.containsKey(val)) {
            return false;
        }
        indexByValue.put(val, values.size());
        values.add(val);
        return true;
    }

    public boolean remove(int val) {
        Integer index = indexByValue.remove(val);
        if (index == null) {
            return false;
        }
        int last = values.removeLast();
        if (index < values.size()) { // удаляли не последний: переносим последний на освободившееся место
            values.set(index, last);
            indexByValue.put(last, index);
        }
        return true;
    }

    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }
}
