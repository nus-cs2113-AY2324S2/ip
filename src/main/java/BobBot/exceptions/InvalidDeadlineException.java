package BobBot.exceptions;
public class InvalidDeadlineException extends BobBotExceptions {

    @Override
    public void displayExceptionMessage() {
        System.out.println("\tIt seems that you have missed out on certain fields for deadline. "
                + "\n\tUsage: deadline {description of task} /by {due date}"
                + "\n\tPlease try again, or enter /help if you need it");
    }   
}
