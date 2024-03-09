/**
 * Implements an exception class for Eln
 * This is the custom exceptions for handling invalid deadline commands.
 *
 * @author nigelheng
 * @since February 2024
 * @version 1.0
 */
public class InvalidDeadlineException extends ElnException {

    @Override
    public void printExceptionMessage() {
        System.out.println("PLEASE follow the format. Try writing");
        System.out.println("deadline (task to be completed) /by (when)");
        System.out.println("Make sure to include /by when and ask Eln for /help if you want.");
    }

}

