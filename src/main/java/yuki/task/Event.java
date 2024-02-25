package yuki.task;

/**
 * Represents an event, which has a starting date and ending date in its description.
 */
public class Event extends Task {

    public Event(String description, boolean isDone) {
        super(description, isDone);
        // set marker as [E] for printing.
        this.taskType = "[E]";
    }

}
