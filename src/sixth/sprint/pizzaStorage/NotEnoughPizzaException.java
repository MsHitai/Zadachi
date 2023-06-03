package sixth.sprint.pizzaStorage;

public class NotEnoughPizzaException extends Exception {
    public NotEnoughPizzaException() {
    }

    public NotEnoughPizzaException(String message) {
        super(message);
    }
}
