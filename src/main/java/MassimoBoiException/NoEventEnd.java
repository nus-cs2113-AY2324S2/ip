package MassimoBoiException;

/**
 * Handles the case when Event has no end date mentioned.
 */
public class NoEventEnd extends EventException{
    /**
     * Prints out the error message.
     */
    @Override
    public void errorMessage(){
        System.out.println("No end date mentioned! After your /from {date}, type /to {date}");
    }
}
