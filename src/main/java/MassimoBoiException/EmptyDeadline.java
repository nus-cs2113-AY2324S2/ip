package MassimoBoiException;

/**
 * Handles Deadlines with no description
 */
public class EmptyDeadline extends DeadlineException{
    /**
     * Prints out the error message.
     */
    @Override
    public void errorMessage(){
        System.out.println("Ma G! You got no description for deadline. Please re-add deadline with description.");
    }
}
