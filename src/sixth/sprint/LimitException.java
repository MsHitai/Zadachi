package sixth.sprint;

public class LimitException extends RuntimeException{

    private final int attempts;

    public LimitException(String message, int attempts) {
        super(message);
        this.attempts = attempts;
    }

    public String getDetailMessage() {
        return getMessage() + attempts;
    }
}
