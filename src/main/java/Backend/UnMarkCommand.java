package Backend;
import tasks.Task;
import java.io.IOException;
import java.util.List;

public class UnMarkCommand extends Command{

    private final int taskNum;

    public UnMarkCommand(int num) {
        this.taskNum = num;
    }

    @Override
    public void execute(List<Task> tasks) {
        if (taskNum <= tasks.size() && taskNum != 0){
            tasks.get(taskNum-1).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(taskNum-1));
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
