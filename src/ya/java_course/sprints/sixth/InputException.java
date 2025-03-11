package ya.java_course.sprints.sixth;

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
