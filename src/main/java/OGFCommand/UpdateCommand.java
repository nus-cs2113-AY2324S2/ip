package OGFCommand;

import OGFCore.OGFException;
import OGFCore.Storage;
import OGFCore.TaskList;
import OGFCore.Ui;

import java.io.IOException;

public class UpdateCommand extends Command{
    boolean isMark;
    int taskToMark;

    public UpdateCommand(int taskToMark, boolean isMark){
        this.taskToMark = taskToMark;
        this.isMark = isMark;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws OGFException {
        try{
            taskList.updateTaskDone(taskToMark, isMark);
            ui.printMarkMessage(isMark, taskList.getTask(taskToMark));
            storage.updateFile(taskList);

        }
        catch(IndexOutOfBoundsException e ){
            throw new OGFException("Tried to access item not in list index. Try again with a smaller number", false);
        }
        catch(IOException e ){
            throw new OGFException("Encountered an error trying to access hard drive file. Closing program.", true);
        }
        return true;
    }
}
