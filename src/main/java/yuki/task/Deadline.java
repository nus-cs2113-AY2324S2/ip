package yuki.task;

public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
        // set marker as [D] for printing.
        this.taskType = "[D]";
    }

}
