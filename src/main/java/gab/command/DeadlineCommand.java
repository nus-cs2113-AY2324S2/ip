package gab.command;

import gab.task.Deadline;
import gab.task.TaskList;
import gab.ui.Ui;

public class DeadlineCommand extends Command {
    private final String TASK_NAME;
    private final String BY;
    public DeadlineCommand (String taskName, String by) {
        this.TASK_NAME = taskName;
        this.BY = by;
    }

    @Override
    public void execute (String task, TaskList taskList) {
        Deadline newDeadline = new Deadline(TASK_NAME, BY);
        taskList.addToList(newDeadline);
        Ui.printDeadlineTask(newDeadline.toString());
        Ui.printTaskCount(taskList.getTaskCount());
    }
}
