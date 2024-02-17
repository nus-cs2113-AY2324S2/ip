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

    /**
     * Returns the string representation of the task for storage.
     * 
     * @param delimiter The delimiter to be used.
     * @return String representation of the task for storage.
     */
    public String toStorageString(String delimiter) {
        int completion = this.isDone ? 1 : 0;
        return String.format("%s%s%d%s%s",
                this.type, delimiter, completion, delimiter, this.name);
    }
}
