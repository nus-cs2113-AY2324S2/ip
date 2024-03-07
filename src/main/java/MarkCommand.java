/**
 * This class represents a command that marks a task as completed in the task list.
 */
public class MarkCommand implements Command {

    private final int index;

    /**
     * Constructor for the MarkCommand class.
     *
     * @param index the index of the task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task at the specified index as completed
     * and printing a confirmation message with the task description.
     *
     * @param taskList the TaskList object containing the tasks
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.getTask(index).markList();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskList.getTask(index).getStatusIcon() + " " + taskList.getTask(index).getDescription());
    }
}


