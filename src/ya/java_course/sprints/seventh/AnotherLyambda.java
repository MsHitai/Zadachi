package ya.java_course.sprints.seventh;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

class Person {
    private String name;
    private String favouriteSpice;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Predicate<Person> isOlderPredicate = (s1) -> {

        return (this.age < s1.age);
    };

    public Person(String name, String favouriteSpice) {
        this.name = name;
        this.favouriteSpice = favouriteSpice;
    }

    public String getName() {
        return name;
    }

    public String getFavouriteSpice() {
        return favouriteSpice;
    }

    public UnaryOperator<String> addFavouriteSpice() {
        return new Adder(this);
    }
}

class Adder implements UnaryOperator<String> {
    private String favouriteSpice;
    private Person person;

    public Adder(Person person) {
        this.person = person;
        this.favouriteSpice = person.getFavouriteSpice();
    }

    @Override
    public String apply(String recipe) {
        return recipe + " " + favouriteSpice;
    }
}

public class AnotherLyambda {

    public static void main(String[] args) {
        Person petya = new Person("Петя", "тмин");

        String oldRecipe = "тесто томаты курица сыр";
        UnaryOperator<String> petyaAdder = petya.addFavouriteSpice();

        String newRecipe = petyaAdder.apply(oldRecipe);
        System.out.println(newRecipe);
    }
}

