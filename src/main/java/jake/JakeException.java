package jake;

/**
 * Returns exceptions that may occur when using Jake.
 * Handle Jake-specific exceptions during running of Jake.
 */
public class JakeException extends Exception {
    public JakeException(String message) {
        super(message);
    }
    public JakeException(String message, Throwable cause) {
        super(message, cause);
    }
}
