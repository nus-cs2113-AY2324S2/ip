package yuki.task;

public class Event extends Task {

    public Event(String description, boolean isDone) {
        super(description, isDone);
        // set marker as [E] for printing.
        this.taskType = "[E]";
    }

}
