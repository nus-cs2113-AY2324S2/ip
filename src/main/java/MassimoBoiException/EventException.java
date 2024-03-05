package MassimoBoiException;

/**
 * Handles Event related exceptions.
 */
public class EventException extends MassimoBoiException{
    /**
     * Prints out the error message.
     */
    @Override
    public void errorMessage(){
        System.out.println("You have an error related to adding an event.");
    }
}
