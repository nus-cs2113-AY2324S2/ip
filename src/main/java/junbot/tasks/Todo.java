package junbot.tasks;

import junbot.tasks.Task;

public class Todo extends Task {
    protected String tag;
    public Todo(String description) {
        super(description);
        this.tag = "T";
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public String toString() {
        return "[" + tag + "]" + "[" + super.getStatusIcon() + "] "
                + description ;
    }

}