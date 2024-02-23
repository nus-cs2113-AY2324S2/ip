package gab.command;

import gab.task.Deadline;
import gab.task.TaskList;
import gab.ui.Ui;

/**
 * Command to add new deadline in taskList
 */

public class DeadlineCommand extends Command {
    private final String TASK_NAME;
    private final String BY;

    /**
     * Construct and initialise new deadline task with name and deadline
     *
     * @param taskName deadline task name to display
     * @param by deadline of task
     */

    public DeadlineCommand (String taskName, String by) {
        this.TASK_NAME = taskName;
        this.BY = by;
    }

    /**
     * Create a new deadline task in the taskList
     *
     * @param taskList arraylist of task
     */

    @Override
    public void execute (TaskList taskList) {
        Deadline newDeadline = new Deadline(TASK_NAME, BY);
        taskList.addToList(newDeadline);
        Ui.printDeadlineTask(newDeadline.toString());
        Ui.printTaskCount(taskList.getTaskCount());
    }
}
