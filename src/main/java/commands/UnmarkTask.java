package commands;
import Exceptions.ThawException;
import Tasks.Task;
import java.util.ArrayList;

public class UnmarkTask extends Command {
    public static void unmarkTask(ArrayList<Task> task, String usersInput) throws ThawException {
        if (!commandWithoutDescription(usersInput)) {
            int taskIndex = Integer.parseInt(usersInput.substring(7)) - 1;
            System.out.println("OK, I've marked this task as not done yet:");
            task.get(taskIndex).setDone(true);
            System.out.println(task.get(taskIndex).getStatusIcon());
        } else if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + usersInput);
        }
    }
}
