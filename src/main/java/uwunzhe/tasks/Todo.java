package uwunzhe.tasks;

public class Todo extends Task {
    /**
     * Constructor for Todo.
     * 
     * @param name The name of the todo.
     */
    public Todo(String name) {
        super(name);
        this.type = TaskType.TODO.getType();
    }

    /**
     * Constructor for Todo.
     * 
     * @param name The name of the todo.
     * @param isDone The status of the todo.
     */
    public Todo(String name, boolean isDone) {
        super(name);
        this.type = TaskType.TODO.getType();
        this.isDone = isDone;
    }

    /**
     * Prints the task and its status in one line.
     * 
     * @param None
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String completion = this.isDone ? "X" : " ";
        return String.format("[%s][%s] %s",
                this.type, completion, this.name);
    }
}
