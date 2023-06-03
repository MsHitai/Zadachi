package sixth.sprint.passwordException;

public class NameValidator implements Validator{
    @Override
    public void validate(String value) throws ValidateException {
        if (value.isBlank() || value.isEmpty()) {
            throw new ValidateNameException("Имя не должно быть пустым");
        }
    } // допишите код класса

}
