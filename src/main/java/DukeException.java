public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }

    // Override toString() to print a custom error statement
    @Override
    public String toString() {
        return "Custom Exception: " + getMessage();
    }

}
