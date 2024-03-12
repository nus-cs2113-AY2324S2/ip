package MassimoBoiException;

/**
 * Handles Deadline related exceptions.
 */
public class DeadlineException extends MassimoBoiException {
    /**
     * Prints out the error message.
     */
    @Override
    public void errorMessage(){
        System.out.println("You have an error related to adding a deadline.");
    }
}
