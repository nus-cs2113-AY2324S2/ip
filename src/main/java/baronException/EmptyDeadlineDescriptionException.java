package baronException;

public class EmptyDeadlineDescriptionException extends BaronException{
    public EmptyDeadlineDescriptionException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
