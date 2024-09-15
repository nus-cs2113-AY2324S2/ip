package alpaca.commands;

import alpaca.tasks.TaskList;
import alpaca.ui.Ui;

/**
 * Represents a command to list all the tasks in the task list
 */
public class ListCommand extends AlpacaCommand{

    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    /**
     * List all the tasks in the task list
     */
    @Override
    public void execute(){
        Ui.printTaskList(tasks);
    }

}
