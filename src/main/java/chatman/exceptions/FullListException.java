package chatman.exceptions;


/**
 * Implements custom exception class instantiated when number of stored tasks is at maximum and
 * user-entered command attempts to add another.
 *
 * @author LWachtel1
 * */
public class FullListException extends ChatManException{

    /**
     * Constructor for FullListException class.
     *
     * @param commandType Command type entered by user for which exception was thrown.
     * @param erroneousInput String storing specific problem with user input which resulted in exception.
     **/
    public FullListException(String commandType, String erroneousInput) {
        super(commandType, erroneousInput);
    }

    /**
     * Prints error message notifying user number of stored tasks is at maximum.
     * */
    @Override
    public void sendErrorMsg() {
        System.out.printf("%s%n%n", "____________________________________________________________");
        System.out.println("List of tasks is full.\nCannot add anymore.");
    }
}
