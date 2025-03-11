package ya.java_course.sprints.sixth.pizzaStorage;

public class TooMuchPizzaException extends Exception {

    public TooMuchPizzaException() {
    }

    public TooMuchPizzaException(String message) {
        super(message);
    }
}
