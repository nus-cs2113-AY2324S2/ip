package jake;
public class JakeException extends Exception {
    public JakeException(String message) {
        super(message);
    }
    public JakeException(String message, Throwable cause) {
        super(message, cause);
    }
}
