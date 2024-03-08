/**
 * The {@code Todo} class represents a to-do item.
 * the super class of all types of tasks
 */
public class Todo {

    /**
     * Indicates whether the to-do item is done or not.
     */
    public Boolean isDone;

    /**
     * The description of the to-do item.
     */
    public String description;

    /**
     * The icon representing the to-do item, defaulting to "T".
     */
    String Icon = "T";

    /**
     * Constructs a new to-do item.
     *
     * @param isDone      Indicates whether the todo item is done
     * @param description The description of the todo item
     */
    public Todo(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Sets the completion status of the to-do item.
     *
     * @param isDone Indicates whether the to-do item is done
     */
    public void SetisDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Sets the description of the to-do item.
     *
     * @param description The description of the to-do item
     */
    public void SetDescription(String description) {
        this.description = description;
    }

    /**this toString method add prefix with brackets to indicate the type and status of the tasks
     * Returns the string representation of the to-do item.
     *
     * @return The string representation of the to-do item
     */
    @Override
    public String toString() {
        String DoneIcon = isDone ? "x" : " ";
        return "[" + Icon + "]" + "[" + DoneIcon + "] " + description;
    }
}