public class DeadlineCommand implements Command {
    public static final int DEADLINE_START_INDEX = 9; //to get deadline name

    private String taskName;
    private String by;
    public DeadlineCommand (String taskName, String by) {
        this.taskName = taskName;
        this.by = by;
    }

    @Override
    public void execute (String task, Task[] taskList) {
        Deadline newDeadline = new Deadline(taskName, by);
        taskList[Task.getTaskCount() - 1] = newDeadline;
        Ui.printDeadlineTask(newDeadline.toString());
        Ui.printTaskCount(Task.getTaskCount());
    }
}
