/**
 * Todo class represents a todo task with description.
 * It extends the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo (String description) {
        super(description);
        this.taskType = "[T]";
    }

    /**
     * Returns the todo task in file format.
     *
     * @return The todo task in file format.
     */
    public String taskInFileFormat() {
        boolean isDone = false;
        if (getStatusIcon().equals("[X]")) {
            isDone = true;
        }
        return "T | " + (isDone ? "1" : "0") + " | " + this.description + "\n";
    }

    /**
     * Prints the todo task with its type, status icon, and description.
     */
    @Override
    public void printTask() {
        System.out.println(taskType + getStatusIcon() + " " + description);
    }
}

