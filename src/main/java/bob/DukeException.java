package bob;

public class DukeException extends Exception {
    public DukeException(String message) {
        super("ERROR: " + message);
    }
}
