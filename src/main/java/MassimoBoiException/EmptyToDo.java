package MassimoBoiException;

/**
 * Handles the case when todo has no description.
 */
public class EmptyToDo extends MassimoBoiException{
    /**
     * Prints out the error message.
     */
    @Override
    public void errorMessage(){
        System.out.println("Ma G! Your todo cannot have empty description! Rewrite the todo with description!");
    }
}
