package BobBot.exceptions;
public class InvalidEventException extends BobBotExceptions {

    @Override
    public void displayExceptionMessage() {
        System.out.println("\tIt seems that you have missed out on certain fields for event. "
                + "\n\tUsage: event {description of task} /from {start date/time} /to {end date/time}"
                + "\n\tPlease try again, or enter /help if you need it");
    }
}
