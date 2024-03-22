package chatman.commands;

import chatman.exceptions.FullListException;
import chatman.exceptions.IncorrectArgumentNumException;
import chatman.exceptions.IncorrectFormatException;
import chatman.tasks.Todo;
import chatman.utility.Tasklist;

/**
 * Implements functionality to enable ChatMan response to user-entered "todo DESCRIPTION" command.
 *
 * @author LWachtel1
 */
public class TodoCommand extends TaskCommand {

    /**
     * Constructor for Todocommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from Parser object) to use in perform()
     * method.
     */
    public TodoCommand(String userCommand) {
        super(userCommand);
    }

    /**
     * Parses todo command to extract its description, then instantiates new Todo object & adds this to task arraylist.
     *
     * @throws FullListException If task arraylist size equals MAX_NUM_TASKS when attempting to add new Todo object.
     * @throws IncorrectArgumentNumException If command provided with incorrect number of arguments.
     * */
    @Override
    public void perform() throws IncorrectArgumentNumException, FullListException, IncorrectFormatException {
        String[] toDoCommand = userCommand.split(" ", 2);

        //Ensures an argument has been provided (todo DESC_ARG)
        if (toDoCommand.length != 2) {
            throw new IncorrectArgumentNumException("TODO",userCommand);
        }

        //checks for any / which would mean unnecessary arguments
        for (String argument: toDoCommand) {
            if (argument.contains("/")) {
                throw new IncorrectArgumentNumException("TODO", argument);
            }
        }


        if (Tasklist.getSize() == Tasklist.MAX_NUM_TASKS) {
            throw new FullListException("TODO", userCommand);
        }

        String toDoDesc = toDoCommand[1].trim();

        if (toDoDesc.isEmpty()) {
            throw new IncorrectFormatException("TODO", userCommand);
        }

        Tasklist.addTask(new Todo(toDoDesc, userCommand));


    }
}
