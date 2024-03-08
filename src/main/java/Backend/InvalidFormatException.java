package Backend;

/**
 * This class represents Expections expected from the StringValidator class.
 */
public class InvalidFormatException extends Exception{
    public InvalidFormatException(String message) {
        super(message);
    }
}
