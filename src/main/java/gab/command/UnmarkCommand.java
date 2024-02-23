package gab.command;

import gab.task.Task;
import gab.task.TaskList;
import gab.ui.Ui;

public class UnmarkCommand extends Command {
    private final String INDEX_TASK;

    public UnmarkCommand(String indexTask) {
        this.INDEX_TASK = indexTask;
    }

    @Override
    public void execute (TaskList taskList) {
        int index = Integer.parseInt(INDEX_TASK) - 1;
        Task taskToMark = taskList.taskList.get(index);
        taskToMark.markAsNotDone();
        Ui.printUnmarkTask(index, taskList);
    }
}
