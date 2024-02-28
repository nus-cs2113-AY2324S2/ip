package commands;

import Tasks.Task;
import Tasks.TaskList;
import Exceptions.ThawException;

import java.util.ArrayList;

/**
 * The FindTask class represents a command to find tasks containing a specific keyword in their descriptions.
 * It extends the Command class and requires the user's input and the list of tasks.
 */
public class FindTask extends Command {

    /**
     * Constructs a FindTask command with the specified user's input and list of tasks.
     *
     * @param usersInput The user's input for the command.
     * @param tasks      The list of tasks to search within.
     * @throws ThawException Thrown if the user's input is empty.
     */
    public FindTask(ArrayList<Task> tasks, String usersInput)  throws ThawException {
        TaskList foundTasks = new TaskList();
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + usersInput);
        } else if (!commandWithoutDescription(usersInput)) {
            ui.printFoundCommandAcknowledgementMessage();
            for (Task task : tasks) {
                if (task.getDescription().contains(usersInput.substring(5))) {
                    foundTasks.addTask(task);
                }
            }
            foundTasks.printTask();
        }
    }
}
