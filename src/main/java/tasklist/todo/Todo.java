package tasklist.todo;

/**
 * Parent class of Event and Deadline.
 * Store description/status/type of tasks and formatTask to be saved and printed accordingly.
 */
public class Todo {
    protected String description;
    protected String status;
    protected String type;

    public Todo(String description) {
        this.description = description;
        status = "[ ]";
        type = "[T]";
    }

    public String formatTask() {
        return (type + status + description);
    }

    public String getWriteFormat() {
        return (type + status + description);
    }

    public void markStatus() {
        if (this.status.equals("[ ]")) {
            this.status = "[X]";
        } else {
            this.status = "[ ]";
        }
    }
}
