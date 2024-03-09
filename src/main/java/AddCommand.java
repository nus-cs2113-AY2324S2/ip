/**
 * Command to add a task.
 */
public class AddCommand implements Command {

    private String taskDescription;

    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasksList) {
        // Implement the logic to add a task to the tasksList
    }
}
