package fifth.sprint;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashSetofBoughtGoods {
    private static List<String> allPurchases = List.of(
            "яблоки",
            "молоко",
            "колбаса",
            "огурцы",
            "сок",
            "хлеб",
            "виноград",
            "молоко",
            "йогурт",
            "хлеб",
            "пельмени"
    );

    public static void main(String[] args) {
        // переменная uniquePurchases должна содержать множество уникальных товаров

        //Set<String> uniquePurchases = new HashSet<>(allPurchases);

        Set<String> uniquePurchases = findUniquePurchases(allPurchases);


        // допишите вывод количества уникальных товаров
        System.out.println("За месяц было куплено " + uniquePurchases.size() + " уникальных товаров.");
    }

    // реализуйте этот метод
    public static HashSet<String> findUniquePurchases(List<String> allPurchases) {
        return new HashSet<>(allPurchases);
    }
}
