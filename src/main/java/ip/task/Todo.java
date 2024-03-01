package ip.task;

/**
 * A subclass of the Task class. Does not contain additional information
 */
public class Todo extends Task {
    private static final int TASK_START_INDEX = 5;

    /**
     * Constructor to process the user's input and call the constructor of the
     * superclass with the appropriate information of the task's description passed
     *
     * @param line the user's input
     */
    public Todo(String line) {
        super(line.substring(TASK_START_INDEX));
    }

    /**
     * Overloaded constructor that takes in the description
     * and whether the todo is done directly instead of the user's input.
     * Calls the constructor of the superclass with the description passed
     * and assigns the isDone attribute
     *
     * @param isDone whether the todo is done
     * @param description the description of the todo
     */
    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a formatted String containing the icon that represents the
     * task type (Todo), the status icon, and the description of the task
     *
     * @return a String specifying the object is a Todo, if it is done, and its description
     */
    @Override
    public String getDetails() {
        return ("[T]" + super.getDetails());
    }
}
