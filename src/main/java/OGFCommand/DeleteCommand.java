package OGFCommand;

import OGFCore.OGFException;
import OGFCore.Storage;
import OGFCore.TaskList;
import OGFCore.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{
    private final int taskToDelete;

    public DeleteCommand(int task){
        taskToDelete = task;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws OGFException{
        try {
            System.out.println("Deleting task: " + taskList.getTask(taskToDelete));
            taskList.deleteTask(taskToDelete);
            ui.printList(taskList);
            storage.updateFile(taskList);
            }
        catch(IndexOutOfBoundsException e){
            throw new OGFException("Tried to access item not in list index. Try again with a smaller number", false);
        }
        catch(IOException e){
            throw new OGFException("Encountered an error trying to access hard drive file. Closing program.", true);
            }
        return true;
    }
}
