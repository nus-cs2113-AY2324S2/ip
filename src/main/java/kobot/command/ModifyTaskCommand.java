package kobot.command;

import kobot.task.TaskList;
import kobot.ui.Ui;

public abstract class ModifyTaskCommand extends Command {
    protected int index;
    
    public ModifyTaskCommand(TaskList taskList) {
        super(taskList);
        this.canExecute = false;
    }

    /**
     * Checks that the index of the tasklist
     * provided is a valid integer. If the
     * index is valid, set the command to
     * be executable.
     *
     * @param index The index of the tasklist.
     * @throws NumberFormatException If the index is not a number.
     */
    public void setIndex(String index) throws NumberFormatException {
        this.index = Integer.parseInt(index) - 1;
        this.canExecute = true;
    }
    
    public abstract void execute();
}
