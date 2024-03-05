package MassimoBoiException;

/**
 * Handles the case when Event has no start date mentioned.
 */
public class NoEventStart extends EventException{
    /**
     * Prints out the error message.
     */
    @Override
    public void errorMessage(){
        System.out.println("No start date mentioned! After your description type /from {date}");
    }
}
