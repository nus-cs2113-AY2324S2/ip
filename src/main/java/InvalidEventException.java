/**
 * Implements an exception class for Eln
 * This is the custom exceptions for handling invalid event commands.
 *
 * @author nigelheng
 * @since February 2024
 * @version 1.0
 */
public class InvalidEventException extends ElnException {

    @Override
    public void printExceptionMessage() {
        System.out.println("Need help describing the event? Try writing");
        System.out.println("event (name of event) /from (start date) /to (end date)");
        System.out.println("Need any more /help, just call Eln.");
    }

}

