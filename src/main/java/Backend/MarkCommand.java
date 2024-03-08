package Backend;
import tasks.Task;
import java.io.IOException;
import java.util.List;

/**
 * This class represents the Mark command that is used to mark a task as complete.
 * Derived from the Command Super Class.
 */
public class MarkCommand extends Command{
    private final int taskNum;

    public MarkCommand(int num) {
        this.taskNum = num;
    }

    /**
     * Performs the action of marking the task specific at TaskNum as Done.
     *
     * @param tasks Given a List of Task Objects.
     */
    @Override
    public void execute(List<Task> tasks) {
        if (taskNum <= tasks.size() && taskNum != 0){
            tasks.get(taskNum-1).setDone(true);
            System.out.println("OK, I've marked this task done:\n" + tasks.get(taskNum-1));
        } else{
            System.out.println("[ERROR] Task number out of bounds: try again");
        }

        try {
            DataManager.saveData(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
