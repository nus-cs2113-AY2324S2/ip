package baronException;

public class EmptyToDoDescriptionException extends BaronException {
    public EmptyToDoDescriptionException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
