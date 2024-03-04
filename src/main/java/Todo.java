public class Todo extends Task{

    /**
     * Creates and instance of the To-do task.
     *
     * @param description Description of task.
     * @param isComplete Completeness of the task.
     */
    public Todo(String description, boolean isComplete) {
        super(description);
        this.isDone = isComplete;
    }

    /**
     * Returns the description of the To-do task in the proper string format.
     */
    @Override
    public String getDescription(){
        return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }
}
