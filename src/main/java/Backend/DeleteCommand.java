package Backend;
import tasks.Task;
import java.io.IOException;
import java.util.List;

/**
 * This class represents the Delete command that is used to remove a task from your todolist.
 * Derived from the Command Super Class.
 */
public class DeleteCommand extends Command{

    private final int taskNum;

    public DeleteCommand(int num) {
        this.taskNum = num;
    }

    @Override
    public void execute(List<Task> tasks) {
        if (taskNum <= tasks.size() && taskNum != 0) {
            System.out.println(
                    "Noted. I've removed this task:\n" +
                    "  " + tasks.get(taskNum - 1) + "\n" +
                    "Now you have " + (tasks.size() - 1) + " tasks in the list.");
            tasks.remove(taskNum - 1);
        } else {
            System.out.println("[ERROR] Task number out of bounds: try again");
        }

        try {
            DataManager.saveData(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
