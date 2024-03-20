package chatman.commands;

import chatman.ChatMan;
import chatman.exceptions.FullListException;
import chatman.exceptions.IncorrectArgumentNumException;
import chatman.tasks.Todo;

/**
 * Implements functionality to enable ChatMan response to user-entered "todo DESCRIPTION" command.
 *
 * @author LWachtel1
 * */
public class TodoCommand extends TaskCommand{

    /**
     * Constructor for Todocommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from CommandParser object) to use in perform()
     * method.
     * */
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
    public void perform() throws IncorrectArgumentNumException, FullListException{
        String[] toDoCommand = userCommand.split(" ", 2);

        //makes sures a description has been provided (todo DESC)
        if (toDoCommand.length != 2) {
            throw new IncorrectArgumentNumException();
        }

        //checks for any / which would mean unnecessary arguments
        for (String argument: toDoCommand) {
            if (argument.contains("/")) {
                throw new IncorrectArgumentNumException();
            }
        }


        if (ChatMan.storedTasks.size() == ChatMan.MAX_NUM_TASKS) {
            throw new FullListException();
        }

        String toDoDesc = toDoCommand[1].trim();

        if (toDoDesc.isEmpty()) {
            //replace with New Exception Type
            throw new IncorrectArgumentNumException();
        }

        ChatMan.storedTasks.add(new Todo(toDoDesc));

        super.replyAddedTask();

    }
}
