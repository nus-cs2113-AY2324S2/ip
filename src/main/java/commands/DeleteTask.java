package commands;
import java.io.IOException;
import java.util.ArrayList;

import Exceptions.ThawException;
import Tasks.*;

/**
 * The DeleteTask class represents a command to delete a task from the task list.
 * It extends the Command class and requires the list of tasks and the user's input.
 */
public class DeleteTask extends Command {

    /**
     * Constructs a new {@code DeleteTask} command with the specified list of tasks and user's input.
     *
     * @param task     The list of tasks to operate on.
     * @param usersInput The user's input for the command.
     * @throws ThawException Thrown if the user's input is empty.
     */
    public DeleteTask(ArrayList<Task> task, String usersInput) throws ThawException {
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + usersInput);
        } else if (!commandWithoutDescription(usersInput)){
            int taskIndex = Integer.parseInt(usersInput.substring(7)) - 1;
            System.out.println("Noted. I've removed this task:");
            System.out.println(task.get(taskIndex).getStatusIcon());
            System.out.println("Now you have " + (task.size() - 1) + " tasks in the list.");
            task.remove(taskIndex);
        }
    }
}
