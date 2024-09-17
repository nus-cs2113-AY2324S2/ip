package BobBot.exceptions;

/**
 * Implements a custom exception that is thrown when the user does not enter
 * the deadline information correctly.
 * 
 * @author NicholasTanYY
 * @since January 2024
 * @version 1.0
 */
public class InvalidDeadlineException extends BobBotExceptions {

    @Override
    public void displayExceptionMessage() {
        System.out.println("\tIt seems that you have missed out on certain fields for deadline. "
                + "\n\tUsage: deadline {description of task} /by {due date}"
                + "\n\tPlease try again, or enter /help if you need it");
    }   
}
