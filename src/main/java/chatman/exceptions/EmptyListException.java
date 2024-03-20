package chatman.exceptions;

/**
 * Implements custom exception class instantiated when command entered acts on empty list.
 *
 * @author LWachtel1
 * */
public class EmptyListException extends ChatManException {

    /**
     * Constructor for EmptyListException class.
     **/
    public EmptyListException() {

    }

    /**
     * Prints error message notifying user that list is empty so command cannot be performed.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");
        System.out.println("The stored list of tasks is currently empty." +
                "\nSo, the entered command cannot be performed");
    }
}
