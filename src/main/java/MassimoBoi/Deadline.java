package MassimoBoi;

public class Deadline extends Task {
    private String deadline;

    /**
     * Creates a new deadline task.
     *
     * @param description the description of the task.
     * @param dueDate the due date.
     */
    public Deadline(String description, String dueDate){
        super(description + String.format("(by:%s)",dueDate));
    }

    /**
     * Returns the task type as Deadline.
     */
    @Override
    public String taskType(){
        return "[D]";
    }
}
