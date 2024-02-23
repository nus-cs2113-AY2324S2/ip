package gab.command;

import gab.task.Task;
import gab.task.TaskList;
import gab.ui.Ui;

public class MarkCommand extends Command {
    private final int INDEX_TASK;

    public MarkCommand(int indexTask) {
        this.INDEX_TASK = indexTask;
    }

    @Override
    public void execute (String task, TaskList taskList) {
        int index = INDEX_TASK - 1;
        Task taskToMark = taskList.taskList.get(index);
        taskToMark.markAsDone();
        Ui.printMarkTask(index, taskList);
    }
}
