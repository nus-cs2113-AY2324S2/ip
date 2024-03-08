/**
 * Implements an exception class for Eln
 * This is the custom exceptions for handling invalid todo commands.
 *
 * @author nigelheng
 * @since February 2024
 * @version 1.0
 */
public class InvalidTodoException extends ElnException{

    @Override
    public void printExceptionMessage() {
        System.out.println("Heyy!! You can't possibly have nothing Todo right? Try writing");
        System.out.println("todo (task to do)");
        System.out.println("If you need /help, just ask Eln.");
    }

}
