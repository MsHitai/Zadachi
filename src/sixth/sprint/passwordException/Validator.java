package sixth.sprint.passwordException;

public interface Validator {
    void validate(String value) throws ValidateException;
}
