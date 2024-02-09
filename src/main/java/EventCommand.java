public class EventCommand implements Command {
    private TaskList userTasks;
    private String[] taskDetails;
    public EventCommand(TaskList userTasks, String userParams) {
        this.userTasks = userTasks;
        taskDetails = userParams.trim().split("/from|/to");
    }

    @Override
    public void execute() {
        try {
            userTasks.addTask(taskDetails[0].trim(), taskDetails[1].trim(), taskDetails[2].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printMessage("Use format:" + System.lineSeparator() + "event (Task Description) /from (Date) /to (Date)");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
