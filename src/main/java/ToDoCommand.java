public class ToDoCommand implements Command{
    private TaskList userTasks;
    private String taskDescription;
    public ToDoCommand(TaskList userTasks, String userParams) {
        this.userTasks = userTasks;
        taskDescription = userParams;
    }

    @Override
    public void execute() {
        if (taskDescription.isBlank()) {
            Ui.printMessage("Quit fooling me, I do not see any task to add!");
        }
        else {
            userTasks.addTask(taskDescription);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
