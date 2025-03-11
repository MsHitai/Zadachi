package ya.java_course.sprints.sixth.pizzaStorage;

public class NotEnoughPizzaException extends Exception {
    public NotEnoughPizzaException() {
    }

    public NotEnoughPizzaException(String message) {
        super(message);
    }
}
