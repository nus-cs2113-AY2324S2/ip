package gab.command;

import gab.task.TaskList;
import gab.ui.Ui;

/**
 * Command to list all the task in the taskList
 */

public class ListCommand extends Command {
    /**
     * Prints all the task in order from the taskList
     *
     * @param taskList arraylist of task
     */

    @Override
    public void execute(TaskList taskList) {
        Ui.listTask(taskList);
    }
}
