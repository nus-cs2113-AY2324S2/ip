package beefy.command;

import beefy.task.TaskList;
import beefy.ui.Ui;

public class MarkCommand implements Command {
    private TaskList userTasks;
    private String taskDetails;
    public MarkCommand(TaskList userTasks, String userParams) {
        this.userTasks = userTasks;
        taskDetails = userParams;
    }

    @Override
    public void execute() {
        try {
            int taskId = Integer.parseInt(taskDetails);
            userTasks.markTask(taskId);
        } catch (NumberFormatException e) {
            Ui.printMessage("Use format:" + System.lineSeparator() + "mark (TaskId)");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
