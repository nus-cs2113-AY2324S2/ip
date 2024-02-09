public class DeadlineCommand implements Command {
    private TaskList userTasks;
    private String[] taskDetails;

    public DeadlineCommand(TaskList userTasks, String userParams) {
        this.userTasks = userTasks;
        taskDetails = userParams.trim().split("/by");
    }

    @Override
    public void execute() {
        try {
            String taskDescription = taskDetails[0].trim();
            String taskBy = taskDetails[1].trim();
            userTasks.addTask(taskDescription, taskBy);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printMessage("Use format:" + System.lineSeparator() + "deadline (Task Description) /by (Date)");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
