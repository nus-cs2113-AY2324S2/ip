package templates.task;

/**
 * Abstract base class and its subclasses representing different types of tasks within the Mario application.
 * Task is the base class providing common properties such as task description and completion status.
 * Event, Deadline, and ToDo extend Task to implement specific behaviors and properties relevant to their respective types,
 * such as start and end dates for Event, a deadline date for Deadline, and basic task properties for ToDo.
 */

 
public abstract class Task{
    private String taskString = "Null";
    private Boolean completed = false;
    private String typeCode;

    public Task(String args, String typeCode, String typeName) throws Exception {
        if (args.isBlank()) {
            throw new Exception(String.format("The description of a %s cannot be empty.\n", typeName));
        }
        args.strip();
        this.taskString = args;
        this.completed = false;
        this.typeCode = typeCode;
    }

    public Boolean getCompleted() {
        return this.completed;
    }

    public String getTaskString() {
        return this.taskString;
    }

    public void setCompleted(Boolean args) {
        this.completed = args;
    }

    @Override
    public String toString() {
        return String.format("\t[%s][%s] %s", typeCode, (completed ? "X" : " "), taskString);
    }
}
