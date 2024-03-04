package jake.task;
public class ToDo extends Task {

    /**
     * Creates a ToDo object based on user inputs
     *
     * @param description Description of the task
     */
    public ToDo(String description) {
        super(description.substring(5));
    }

    /**
     * Prints the completion status of task, and description of the task
     *
     * @return description of the task and completion status
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
