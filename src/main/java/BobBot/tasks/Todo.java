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

    public Todo(String description) throws InvalidTodoException {
        super(description);
        this.toDo = this.description.substring("todo".length()).trim();

        if (this.toDo.length() < 1) {
            throw new InvalidTodoException();
        }
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.toDo;
    }
}
