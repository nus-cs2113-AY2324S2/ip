package chatman.commands;

import chatman.exceptions.*;

/**
 * Implements Command abstract class as template for all Command-type classes.
 *
 * @author LWachtel1
 * */
public abstract class Command {

    protected String userCommand;

    /**
     * Constructor for Command abstract class; invoked by its concrete subclasses using super() method call.
     *
     * @param userCommand Receives and stores user-entered command (from CommandParser object) to use in perform()
     * method where implemented.
     * */
    public Command(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes intended action of corresponding user-entered command; abstract method to be implemented by concrete
     * subclasses.
     *
     * @throws FullListException If task arraylist size equals MAX_NUM_TASKS when attempting to add Todo, Deadline
     * or Event.
     * @throws IncorrectArgumentNumException If command is provided with incorrect number of arguments.
     * @throws IncorrectMarkUnmarkException If MarkUnmarkCommand provided with non-numerical index or with numerical
     * index beyond task arraylist size.
     * @throws EmptyListException If list of stored tasks is currently empty.
     * @throws IncorrectFormatException If command is entered without required formatting of arguments.
     * */
    public abstract void perform () throws IncorrectArgumentNumException, IncorrectMarkUnmarkException,
            FullListException, EmptyListException, IncorrectFormatException;

}
