package BobBot.exceptions;

/**
 * Implements a custom exception that is thrown when the user does not enter
 * the to-do task information correctly.
 * 
 * @author NicholasTanYY
 * @since January 2024
 * @version 1.0
 */
public class InvalidTodoException extends BobBotExceptions {

    @Override
    public void displayExceptionMessage() {
        System.out.println("\tIt seems that you have not entered the task information. "
                + "\n\tUsage: todo {description of task}"
                + "\n\tPlease try again, or enter /help if you need it");
    }
    
}
