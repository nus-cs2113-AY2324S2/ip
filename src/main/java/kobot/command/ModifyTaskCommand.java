package kobot.command;

import kobot.task.TaskList;
import kobot.ui.Ui;

public abstract class ModifyTaskCommand extends Command {
    protected int index;
    
    public ModifyTaskCommand(TaskList taskList) {
        super(taskList);
        this.canExecute = false;
    }
    
    public void setIndex(String index) throws NumberFormatException {
        this.index = Integer.parseInt(index) - 1;
        this.canExecute = true;
    }
    
    public abstract void execute();
}
