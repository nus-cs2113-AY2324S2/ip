package exceptions;

public class InvalidTaskIndex extends Exception {
    public InvalidTaskIndex(String errorString) {
        super(errorString);
    }
}
