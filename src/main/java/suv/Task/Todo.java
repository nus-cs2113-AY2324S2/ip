package suv.Task;

import suv.Task.Task;

/**
 * The Todo class represents a task without any specific deadline or schedule.
 * It inherits from the Task class and adds functionality specific to Todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo object with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * The representation includes the task type identifier and the description.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
