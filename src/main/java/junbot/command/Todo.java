package junbot.command;

import junbot.task.Task;

public class Todo extends Task {
    protected String tag;
    public Todo(String description) {
        super(description);
        this.tag = "T";
    }

    @Override
    public String toString() {
        return "[" + tag + "]" + "[" + super.getStatusIcon() + "] "
                + description ;
    }
}