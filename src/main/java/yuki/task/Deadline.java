package yuki.task;

public class Deadline extends Task {

    public Deadline(String description, boolean isDone) {
        super(description, isDone);
        // set marker as [D] for printing.
        this.taskType = "[D]";
    }

}
