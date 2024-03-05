package MassimoBoiException;

/**
 * Handles the case when deadline has no due date.
 */
public class NoDueDate extends DeadlineException{
    /**
     * Prints out the error message.
     */
    @Override
    public void errorMessage(){
        System.out.println("No due date mentioned! After your description type /by {date}");
    }
}
