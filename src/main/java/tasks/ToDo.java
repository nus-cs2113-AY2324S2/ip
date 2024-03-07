package tasks;

/**
 * Todo task with details on the description and whether it's complete.
 */
public class ToDo extends Task {

    public ToDo(String userInput, boolean isDone) {
        super(userInput, isDone);
    }

    @Override
    public String toFileString() {
        return String.format("todo|%s|%s", isDone ? "1" : "0", getDescription());
    }

    @Override
    public String taskString() {
        return "[T]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}

