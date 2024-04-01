package commands;
import java.io.IOException;
import java.util.ArrayList;

import Exceptions.ThawException;
import FileManagerPackage.Storage;
import Tasks.*;

/**
 * The DeleteTask class represents a command to delete a task from the task list.
 * It extends the Command class and requires the list of tasks and the user's input.
 */
public class DeleteTask extends Command {

    private final String CLASS_NAME = "delete";

    private final int INDEX_OF_TASK = 7;

    /**
     * Constructs a new {@code DeleteTask} command with the specified list of tasks and user's input.
     *
     * @param task     The list of tasks to operate on.
     * @param usersInput The user's input for the command.
     * @throws ThawException Thrown if the user's input is empty.
     */
    public DeleteTask(ArrayList<Task> task, String usersInput) throws ThawException {
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + CLASS_NAME);
        } else if (!commandWithoutDescription(usersInput)){
            int taskIndex = Integer.parseInt(usersInput.substring(INDEX_OF_TASK)) - 1;
            ui.printDeleteTaskAcknowledgementMessage(task, taskIndex);
            task.remove(taskIndex);
            Storage.saveData(task);
        }
    }
}
