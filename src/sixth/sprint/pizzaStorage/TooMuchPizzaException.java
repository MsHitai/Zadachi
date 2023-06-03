package sixth.sprint.pizzaStorage;

public class TooMuchPizzaException extends Exception {

    public TooMuchPizzaException() {
    }

    public TooMuchPizzaException(String message) {
        super(message);
    }
}
