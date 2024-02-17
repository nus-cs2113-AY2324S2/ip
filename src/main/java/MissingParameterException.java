public class MissingParameterException extends Exception {
    public MissingParameterException(String message) {
        super("Hey, the description of " + message + " is missing");
    }
}
