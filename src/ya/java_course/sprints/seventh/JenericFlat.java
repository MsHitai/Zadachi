package ya.java_course.sprints.seventh;

import java.util.List;
import java.util.stream.Stream;

class Flat {
    private String city;
    private int price;
    private int area;

    public Flat(String city, int price, int area) {
        this.city = city;
        this.price = price;
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public int getPrice() {
        return price;
    }

    public int getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "city='" + city + '\'' +
                ", price=" + price + "$" +
                ", area=" + area + " кв.м." +
                '}';
    }
}

public class JenericFlat {

    public static void main(String[] args) {
        List<Flat> all = List.of(
                new Flat("Амстердам", 150_000, 50),
                new Flat("Санкт-Петербург", 350_000, 130),
                new Flat("Амстердам", 900_000, 150),
                new Flat("Рим", 60_000, 200),
                new Flat("Лондон", 3000, 30)
        );

        /*Predicate<Flat> filter1 = t -> t.getPrice() < 100_000;
        Predicate<Flat> filter2 = t -> t.getArea() > 100;
        Function<Flat, String> transformer = Flat::getCity;
        Consumer<String> targetAction = System.out::println;

        Set<String> values = new HashSet<>();
        // допишите ваш код здесь
        for (Flat flat : all) {
            if (filter1.test(flat) && filter2.test(flat) ) {

                values.add(transformer.apply(flat));
            }
        }

        for (String s : values) {
            targetAction.accept(s);
        }*/

        Stream<Flat> filteredStream = all.stream()
                .filter(flat -> flat.getPrice() < 100_000 && flat.getArea() > 100);
        filteredStream.forEach(System.out::println);
    }
}
