package MassimoBoi;

public class ToDo extends Task{

    /**
     * Creates a new todo task.
     */
    public ToDo(String todoDescription) {
        super(todoDescription);
    }

    /**
     * Returns the task type as todo.
     */
    @Override
    public String taskType(){
        return "[T]";
    }
}
