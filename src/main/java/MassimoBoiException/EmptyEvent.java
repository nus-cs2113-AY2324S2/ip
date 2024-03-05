package MassimoBoiException;

/**
 * Handles the case when Event has no description.
 */
public class EmptyEvent extends EventException{
    /**
     * Prints out the error message.
     */
    @Override
    public void errorMessage(){
        System.out.println("Ma G! You got no description for event. Please re-add event with description.");
    }
}
