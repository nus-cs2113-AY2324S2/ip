package blue.exception;

public class IllegalTaskException extends IllegalInputException {
    private String message;

    public IllegalTaskException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
