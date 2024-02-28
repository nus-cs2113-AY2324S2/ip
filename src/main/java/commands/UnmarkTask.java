package commands;
import Exceptions.ThawException;
import Tasks.Task;
import java.util.ArrayList;

/**
 * The UnmarkTask class represents a command to unmark a completed task.
 * It extends the Command class and requires the list of tasks and the user's input.
 */
public class UnmarkTask extends Command {
    /**
     * The name of the command class (used for error messages).
     */
    public final String className = "unmark";

    /**
     * Constructs an UnmarkTask command with the specified list of tasks and user's input.
     *
     * @param task      The list of tasks to operate on.
     * @param usersInput The user's input for the command.
     * @throws ThawException Thrown if the user's input is empty.
     */
    public UnmarkTask(ArrayList<Task> task, String usersInput) throws ThawException {
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + className);
        } else if (!commandWithoutDescription(usersInput)) {
            int taskIndex = Integer.parseInt(usersInput.substring(7)) - 1;
            task.get(taskIndex).setDone(false);
            ui.printUnmarkTaskAcknowledgementMessage(task, taskIndex);
        }
    }
}
