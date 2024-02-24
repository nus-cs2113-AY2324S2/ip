package commands;
import Exceptions.ThawException;
import Tasks.Task;
import java.util.ArrayList;

public class MarkTask extends Command {
    public static void markTask(ArrayList<Task> task, String usersInput) throws ThawException  {
        if (!commandWithoutDescription(usersInput)) {
            int taskIndex = Integer.parseInt(usersInput.substring(5)) - 1;
            System.out.println("Nice! I've marked this task as done:");
            task.get(taskIndex).setDone(true);
            System.out.println(task.get(taskIndex).getStatusIcon());
        } else if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + usersInput);
        }
    }
}
