package BobBot.tasks;

import BobBot.exceptions.InvalidTodoException;

/**
 * Implements a to-do task that stores the description of the task.
 * 
 * <p> The description is stored in the format <code>[task]</code>.</p>
 * 
 * @author NicholasTan
 * @since January 2024
 * @version 1.0
 */
public class Todo extends Task {

    protected String toDo;

    /**
     * Creates a to-do task with the given description.
     * 
     * @param description The description of the to-do task.
     * @throws InvalidTodoException If the description is empty.
     */
    public Todo(String description) throws InvalidTodoException {
        super(description);
        this.toDo = this.description.substring("todo".length()).trim();

        if (this.toDo.length() < 1) {
            throw new InvalidTodoException();
        }
    }

    /**
     * Returns the string representation of the to-do task.
     * 
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.toDo;
    }
}
