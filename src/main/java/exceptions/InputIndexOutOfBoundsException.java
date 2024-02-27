package exceptions;

/**
 * Exception thrown when an user-specified index is out-of-bounds, defined as an index less than 0
 * or an index exceeding the current number of tasks tracked by Dor
 */
public class InputIndexOutOfBoundsException extends Exception {

    /**
     * Constructor for this exception with an error message indicating that the index is out of bounds
     */
    public InputIndexOutOfBoundsException() {
        super("ERROR: Index out of bounds!");
    }
}
