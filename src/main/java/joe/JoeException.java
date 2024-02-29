package joe;

/**
 * Custom exception for Joe
 */
public class JoeException extends Exception {
    public JoeException(String errorMessage) {
        // Constructor for specific error messages
        super(errorMessage);
    }

    public JoeException() {
        // Empty constructor
    }
}
