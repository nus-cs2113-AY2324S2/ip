package commands;
import Exceptions.ThawException;
import PrintMessages.UI;
import Tasks.Task;
import java.util.ArrayList;

/**
 * The MarkTask class represents a command to mark a task as completed.
 * It extends the Command class and requires the list of tasks and the user's input.
 */
public class MarkTask extends Command {

    /**
     * Constructs a MarkTask command with the specified list of tasks and user's input.
     *
     * @param task      The list of tasks to operate on.
     * @param usersInput The user's input for the command.
     * @throws ThawException Thrown if the user's input is empty.
     */
    public MarkTask(ArrayList<Task> task, String usersInput) throws ThawException  {
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + usersInput);
        } else if (!commandWithoutDescription(usersInput)) {
            int taskIndex = Integer.parseInt(usersInput.substring(5)) - 1;
            task.get(taskIndex).setDone(true);
            ui.printMarkTaskAcknowledgementMessage(task, taskIndex);
        }
    }
}
