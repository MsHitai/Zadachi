package sixth.sprint;

public class InputException extends Exception {

    public InputException() {
    }

    public InputException(String message) {
        super(message);
    }

    public String getDetailMessage() {
        return getMessage() + ": ";
    }
}
