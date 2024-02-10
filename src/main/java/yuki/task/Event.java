package yuki.task;

public class Event extends Task {

    public Event(String description) {
        super(description);
        // set marker as [E] for printing.
        this.taskType = "[E]";
    }

}
