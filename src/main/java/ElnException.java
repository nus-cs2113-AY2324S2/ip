
/**
 * Implements an exception class for Eln
 * This is the parent class for all custom exceptions implemented
 *
 * @author nigelheng
 * @since February 2024
 * @version 1.0
 */
public abstract class ElnException extends Exception {

    public ElnException() {

    }

    public abstract void printExceptionMessage();
}
