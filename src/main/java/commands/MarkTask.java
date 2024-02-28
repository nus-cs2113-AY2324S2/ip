package commands;
import Exceptions.ThawException;
import PrintMessages.UI;
import Tasks.Task;
import java.util.ArrayList;

/**
 *
 */
public class MarkTask extends Command {
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
