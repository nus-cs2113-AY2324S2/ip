package gab.command;

import gab.task.Task;
import gab.task.TaskList;
import gab.ui.Ui;

/**
 * Command to mark task as done
 */
public class MarkCommand extends Command {
    private final int INDEX_TASK;

    /**
     * Initialise the index of the task to mark as done
     *
     * @param indexTask index of task to mark as done
     */
    public MarkCommand(int indexTask) {
        this.INDEX_TASK = indexTask;
    }

    /**
     * Mark task as done and display to the user
     *
     * @param taskList arraylist of task
     */
    @Override
    public void execute (TaskList taskList) {
        int index = INDEX_TASK - 1;
        Task taskToMark = taskList.taskList.get(index);
        taskToMark.markAsDone();
        Ui.printMarkTask(index, taskList);
    }
}
