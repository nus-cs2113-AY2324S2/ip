package gab.command;

import gab.task.Task;
import gab.task.TaskList;
import gab.ui.Ui;

/**
 * Command to un mark task if not done
 */
public class UnmarkCommand extends Command {
    private final String INDEX_TASK;

    /**
     * Initialise index of task to un mark
     *
     * @param indexTask index of task to un mark
     */
    public UnmarkCommand(String indexTask) {
        this.INDEX_TASK = indexTask;
    }

    /**
     * Un mark task and display it to the user
     * Display final task count
     *
     * @param taskList arraylist of task
     */
    @Override
    public void execute (TaskList taskList) {
        int index = Integer.parseInt(INDEX_TASK) - 1;
        Task taskToMark = taskList.taskList.get(index);
        taskToMark.markAsNotDone();
        Ui.printUnmarkTask(index, taskList);
    }
}
