package MassimoBoiException;

/**
 * Handles the case when user enters a command that is not in the predefined list of commands.
 */
public class UnknownCommandType extends MassimoBoiException{
    /**
     * Prints out the error message.
     */
    @Override
    public void errorMessage(){
        System.out.println("I don't know what that means homie! Use one of the commands listed below: ");
    }
}
