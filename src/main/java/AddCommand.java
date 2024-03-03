import java.io.IOException;

/**
 * A Command that stores a Task that when executed, adds that Task to the task list and stores it in hardware
 */
public class AddCommand extends Command{
    private final Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws OGFException {
        try{
            taskList.addTask(task);
            ui.printTaskAdded(task, taskList);
            storage.appendToFile(task);
        }
        catch(IOException error){
            throw new OGFException("Encountered an error trying to access hard drive file. Closing program.", true);
        }
        return true;
    }
}
