package chatman.commands;

import chatman.ChatMan;
import chatman.exceptions.EmptyListException;
import chatman.exceptions.IncorrectArgumentNumException;
import chatman.exceptions.IncorrectMarkUnmarkException;
import chatman.tasks.Task;

/**
 * Implements ChatMan's ability to delete/remove a currently stored task from storage.
 *
 * @author LWachtel1
 * */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from CommandParser object) to use in perform()
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
     * @throws IncorrectMarkUnmarkException If command provided with non-numerical argument or index outside of bounds
     * of task arraylist.
     * @throws EmptyListException If list of stored tasks is currently empty.
     * */
    public void perform() throws IncorrectArgumentNumException, EmptyListException, IncorrectMarkUnmarkException {
        String[] deleteCommand = userCommand.split(" ");

        //Checks for only delete command & 1 argument being present
        if (deleteCommand.length != 2) {
            throw new IncorrectArgumentNumException("DELETE", userCommand);
        }

        if (ChatMan.accessTasks().isEmpty()) {
            throw new EmptyListException("DELETE", userCommand);
        }

        String position = deleteCommand[1];
        int storagePosition = 0;

        try {
            storagePosition = Integer.parseInt(position);
            if (storagePosition > ChatMan.accessTasks().size() || storagePosition <= 0) {
                throw new IncorrectMarkUnmarkException("DELETE",position);
            }
        } catch(NumberFormatException exception) {
            throw new IncorrectMarkUnmarkException("DELETE",position);
        }
        int storageIndex = storagePosition - 1;

        String deletedTask = ChatMan.accessTasks().get(storageIndex).toString();
        ChatMan.accessTasks().remove(storageIndex);

        System.out.printf("%s%n", "____________________________________________________________");
        System.out.printf("Noted. I've removed this task:%n%s%nNow you have %d tasks in the list.%n", deletedTask,
                ChatMan.accessTasks().size());
    }
}
