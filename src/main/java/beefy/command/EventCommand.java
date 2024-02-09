package beefy.command;

import beefy.BeefyException;
import beefy.task.TaskList;
import beefy.ui.Ui;

public class EventCommand implements Command {
    private TaskList userTasks;
    private String taskDescription, taskFrom, taskTo;

    public EventCommand(TaskList userTasks, String userParams) throws BeefyException {
        this.userTasks = userTasks;
        String[] taskDetails = userParams.trim().split("/from|/to");
        if (taskDetails.length < 3) {
            throw new BeefyException("Use format:" + System.lineSeparator()
                    + "event (Task Description) /from (Date) /to (Date)");
        }
        taskDescription = taskDetails[0].trim();
        taskFrom = taskDetails[1].trim();
        taskTo = taskDetails[2].trim();
    }

    @Override
    public void execute() {
            userTasks.addTask(taskDescription, taskFrom, taskTo);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
