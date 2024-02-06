public class Todo extends Task {
    /**
     * Constructor for Todo.
     * 
     * @param name The name of the todo.
     * @return None
     */
    public Todo(String name) {
        super(name);
        this.type = "T";
    }

    /**
     * Prints the task and its status in one line.
     * Overwrites toString method in Java Object class.
     * 
     * @param None
     * @return String representation of the task.
     */
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
