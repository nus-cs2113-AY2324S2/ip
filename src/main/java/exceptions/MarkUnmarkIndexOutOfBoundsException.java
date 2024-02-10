package exceptions;
public class MarkUnmarkIndexOutOfBoundsException extends Exception {
    public MarkUnmarkIndexOutOfBoundsException() {
        super("ERROR: Index out of bounds for marking/unmarking!");
    }
}
