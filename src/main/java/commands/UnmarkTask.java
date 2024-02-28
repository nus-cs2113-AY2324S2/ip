package commands;
import Exceptions.ThawException;
import Tasks.Task;
import java.util.ArrayList;

/**
 * 
 */
public class UnmarkTask extends Command {
    public final String className = "unmark";
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
