
public class InvalidUnmarkException extends BobBotExceptions {

    @Override
    public void displayExceptionMessage() {
        System.out.println("\tThis activity has already been unmarked!"
                + "\n\tUsage: unmark {activity number}"
                + "\n\tPlease try again, or enter /help if you need it");
    }

}
