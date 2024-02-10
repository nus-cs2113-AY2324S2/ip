package exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("ERROR: Invalid input!");
    }
}
