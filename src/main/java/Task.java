public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setStatus(boolean mark) {
        isDone = mark;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileFormat() {
        // Escape characters: usertyped " would not interfere with file format
        String escapedDescription = description.replace("\"", "\\\"");

        return " | " + isDone + " | \"" + description + "\"";
    }
}
