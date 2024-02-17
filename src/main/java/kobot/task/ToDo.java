package kobot.task;

import kobot.task.Task;

public class ToDo extends Task {
    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }
    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return String.format("[T]%s %s", this.getStatusIcon(), this.description);
    }

    public String toStorageFormat() {
        return String.format("T;%b;%s", this.isDone, this.description);
    }
}
