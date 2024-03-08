package tasklist.deadline;
import tasklist.todo.Todo;

/**
 * Inherits from Todo with date to store deadline type of input.
 */
public class Deadline extends Todo {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        type = "[D]";
    }

    public String getDate() {
        return (date);
    }

    public String formatTask() {
        return (type + status + description + "(by:" + date + ")");
    }

    public String getWriteFormat() {
        return (type + status + description + "/by" + date);
    }
}

