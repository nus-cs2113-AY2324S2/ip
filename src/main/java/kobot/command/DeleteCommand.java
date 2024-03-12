package kobot.command;

import kobot.ui.Ui;
import kobot.storage.Storage;
import kobot.task.TaskList;

public class DeleteCommand extends ModifyTaskCommand {
    public DeleteCommand(TaskList taskList, String index) {
        super(taskList);

        try {
            setIndex(index);
        } catch (NumberFormatException exception) {
            Ui.printDeleteCommandUsage();
        }
    }
    
    public void execute() {
        if (!canExecute) {
            return;
        }
        
        try {
            taskList.deleteTask(this.index);
            Storage.updateFile(taskList);
        }  catch (IndexOutOfBoundsException exception) {
            Ui.printIndexOutOfBoundsMessage("delete");
        }
    }
}
