package gab.command;

import gab.task.Event;
import gab.task.TaskList;
import gab.ui.Ui;

public class EventCommand extends Command {
    private final String TASK_NAME;
    private final String START_DATE;
    private final String END_DATE;

    public EventCommand(String taskName, String startDate, String endDate) {
        this.TASK_NAME = taskName;
        this.START_DATE = startDate;
        this.END_DATE= endDate;
    }

    @Override
    public void execute (String task, TaskList taskList) {
        Event newEvent = new Event(TASK_NAME, START_DATE, END_DATE);
        taskList.addToList(newEvent);
        Ui.printEventTask(newEvent.toString());
        Ui.printTaskCount(taskList.getTaskCount());
    }
}
