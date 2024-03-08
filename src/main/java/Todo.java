/*
 * Todo class represents a todo task
 * A <code>Todo</code> object represents a todo task with a description
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo object with the given description.
     *
     * @param description The description of the todo
     */
    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    /**
     * Returns the task type of the todo
     * Overrides the toString method in Task
     */
    public String toString() {
        return "[T]" + super.toString();
    }

}