package gab.command;

import gab.task.Event;
import gab.task.TaskList;
import gab.ui.Ui;

/**
 * Command to create new event class in taskList
 */
public class EventCommand extends Command {
    private final String TASK_NAME;
    private final String START_DATE;
    private final String END_DATE;

    /**
     * Initialise event with name, start date and end date
     *
     * @param taskName event task name to be displayed
     * @param startDate event start date
     * @param endDate event end date
     */

    public EventCommand(String taskName, String startDate, String endDate) {
        this.TASK_NAME = taskName;
        this.START_DATE = startDate;
        this.END_DATE= endDate;
    }

    /**
     * Create new event task to taskList
     * Prints out event that was added and final task count
     *
     * @param taskList arraylist of task
     */

    @Override
    public void execute (TaskList taskList) {
        Event newEvent = new Event(TASK_NAME, START_DATE, END_DATE);
        taskList.addToList(newEvent);
        Ui.printEventTask(newEvent.toString());
        Ui.printTaskCount(taskList.getTaskCount());
    }
}
