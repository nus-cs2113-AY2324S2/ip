package exceptions;

/**
 * Represents an exception thrown when trying to access an item in an ArrayList that is out of bounds.
 * This exception is thrown when the index that is being accessed does not exist in the list.
 */
public class ArrayListOutOfBoundsException extends Exception {

    /**
     * Retrieves the error message for the exception.
     * @return The error message string indicating that the item does not exist in the list
     */
    @Override
    public String getMessage() {
        return "\t This item does not exist in your list. Try a different item.";
    }
}
