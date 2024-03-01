public class Todo extends Task{

    /**
     * Creates and instance of the To-do task.
     *
     * @param description Description of task.
     * @param isDone Completeness of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description);
    }

    /**
     * Returns the description of the To-do task in the proper string format.
     */
    public String getDescription(){
        return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }
}
