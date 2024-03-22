package chatman.commands;

import chatman.exceptions.EmptyListException;
import chatman.exceptions.IncorrectArgumentNumException;
import chatman.exceptions.IncorrectIndexException;
import chatman.utility.Tasklist;
import chatman.utility.Ui;

/**
 * Implements ChatMan's ability to delete/remove a currently stored task from storage.
 *
 * @author LWachtel1
 * */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from Parser object) to use in perform()
     * method.
     * */
    public DeleteCommand(String userCommand) {
        super(userCommand);
    }

    /**
     * Parses delete command into command and index of task to delete, then accesses task object at given index
     * (if valid) & removes.
     *
     * @throws IncorrectArgumentNumException If command provided with incorrect number of arguments.
     * @throws IncorrectIndexException If command provided with non-numerical argument or index outside of bounds
     * of task arraylist.
     * @throws EmptyListException If list of stored tasks is currently empty.
     * */
    public void perform() throws IncorrectArgumentNumException, EmptyListException, IncorrectIndexException {
        String[] deleteCommand = userCommand.split(" ");

        //Checks for only delete command & 1 argument being present
        if (deleteCommand.length != 2) {
            throw new IncorrectArgumentNumException("DELETE", userCommand);
        }

        if (Tasklist.isTasklistEmpty()) {
            throw new EmptyListException("DELETE", userCommand);
        }

        String position = deleteCommand[1];
        int storagePosition = 0;

        try {
            storagePosition = Integer.parseInt(position);
            if (storagePosition > Tasklist.getSize() || storagePosition <= 0) {
                throw new IncorrectIndexException("DELETE",position);
            }
        } catch(NumberFormatException exception) {
            throw new IncorrectIndexException("DELETE",position);
        }
        int storageIndex = storagePosition - 1;

        String deletedTask = Tasklist.getTask(storageIndex).toString();
        Tasklist.removeTask(storageIndex);

        System.out.printf("%s%n", Ui.getChatbotSeparator());
        System.out.printf("Noted. I've removed this task:%n%s%nNow you have %d tasks in the list.%n", deletedTask,
                Tasklist.getSize());
    }
}
