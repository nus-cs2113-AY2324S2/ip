package tasks;

/**
 * The Todo subclass of Task. Requires no additional inputs.
 */
public class Todo extends Task {

    /**
     * Constructor for the todo object.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    /**
     * Constructor for the todo object when restructuring the list
     * from nocturne.txt, which includes the completion status of the todo.
     *
     * @param description The description of the todo.
     * @param isDone The boolean variable of whether the todo is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Override of the todo class when it is printed,
     * resulting in the format shown in the list.
     *
     * @return The string that will be printed.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + description;

    }
}