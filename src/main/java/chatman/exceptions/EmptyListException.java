package chatman.exceptions;

/**
 * Implements custom exception class instantiated when command entered acts on empty list.
 *
 * @author LWachtel1
 * */
public class EmptyListException extends ChatManException {

    /**
     * Constructor for EmptyListException class.
     *
     * @param commandType Command type entered by user for which exception was thrown.
     * @param erroneousInput String storing specific problem with user input which resulted in exception.
     **/
    public EmptyListException(String commandType, String erroneousInput) {
        super(commandType, erroneousInput);
    }

    /**
     * Prints error message notifying user that list is empty so command cannot be performed.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");
        System.out.printf("The stored list of tasks is currently empty."
                + "\nSo, the entered command '%s' cannot be performed\n", super.getErroneousInput());
    }
}
