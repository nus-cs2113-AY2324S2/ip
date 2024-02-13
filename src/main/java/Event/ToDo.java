package Event;

import Event.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.description = description.substring(5).trim(); // Remove "todo" command and trim spaces.
        this.eventType = "[T]";
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
