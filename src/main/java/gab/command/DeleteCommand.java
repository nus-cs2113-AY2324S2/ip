package gab.command;

import gab.task.TaskList;
import gab.ui.Ui;

/**
 * Command to delete task from taskList
 */
public class DeleteCommand extends Command {
    public final int DELETE_INDEX;

    /**
     * Initialise index of task to be deleted
     *
     * @param taskIndex index of task to delete based on taskList
     */
    public DeleteCommand (int taskIndex) {
        this.DELETE_INDEX = taskIndex;
    }

    /**
     * Delete task from array list based on the index input
     * Prints out task that was deleted and final task count
     *
     * @param taskList arraylist of task
     */
    @Override
    public void execute (TaskList taskList) {
        int indexToDelete = DELETE_INDEX - 1;
        Ui.printDeleteTask(indexToDelete, taskList);
        taskList.taskList.remove(indexToDelete);
        Ui.printTaskCount(taskList.getTaskCount());
    }
}