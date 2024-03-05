package blue.exception;

public class IllegalInputException extends BlueException {

    @Override
    public String getMessage() {
        return "I'm sorry. I'm afraid I can't do that.";
    }
}
