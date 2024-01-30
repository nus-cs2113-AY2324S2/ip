public class AddTaskCommand implements Command {
    private final String taskDescription;

    public AddTaskCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(new Task(this.taskDescription));
        System.out.println("added: " + this.taskDescription);
    }
}
