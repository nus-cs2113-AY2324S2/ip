package OGFCommand;

import OGFCore.OGFException;
import OGFCore.Storage;
import OGFCore.TaskList;
import OGFCore.Ui;

import java.io.IOException;

/**
 * A OGFCommand.Command that stores a OGFTask.Task that when executed, adds that OGFTask.Task to the task list and stores it in hardware
 */
public class AddCommand extends Command{
    private final OGFTask.Task task;

    public AddCommand(OGFTask.Task task){
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
