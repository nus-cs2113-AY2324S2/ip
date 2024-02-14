public class EventCommand implements Command {

    private String taskName;
    private String startDate;
    private String endDate;

    public EventCommand(String taskName, String startDate, String endDate) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void execute (String task, Task[] taskList) {
        Event newEvent = new Event(taskName, startDate, endDate);
        taskList[Task.getTaskCount() - 1] = newEvent;
        Ui.printEventTask(newEvent.toString());
        Ui.printTaskCount(Task.getTaskCount());
    }
}
