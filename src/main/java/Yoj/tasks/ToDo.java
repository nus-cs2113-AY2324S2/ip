package Yoj.tasks;
public class ToDo extends Task {
    /**
     * Constructor for the ToDo class.
     * It calls the parent class's constructor to set the task's description.
     * @param description The text description of what the to-do task is.
     *
     */
    public ToDo (String description){
        super(description);
    }
    /**
     * Returns the type of the task, in this case "[T]" to represent a todo task.
     *
     * @return A string indicating the type of the task.
     */
    public String taskType() {
        return "[T]";
    }
    /**
     * Provides a string representation of the to-do task, including its type and description.
     * It overrides the `toString` method in the superclass (Task).
     *
     * @return A string that represents the todo task.
     */
    @Override
    public String toString(){
        return taskType() + super.toString();
    }
}