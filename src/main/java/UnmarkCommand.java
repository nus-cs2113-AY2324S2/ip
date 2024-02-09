public class UnmarkCommand implements Command {
    private TaskList userTasks;
    private String taskDetails;
    public UnmarkCommand(TaskList userTasks, String userParams) {
        this.userTasks = userTasks;
        taskDetails = userParams;
    }

    @Override
    public void execute() {
        try {
            int taskId = Integer.parseInt(taskDetails);
            userTasks.unmarkTask(taskId);
        } catch (NumberFormatException e) {
            Ui.printMessage("Use format:" + System.lineSeparator() + "mark (TaskId)");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

