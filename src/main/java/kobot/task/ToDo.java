package kobot.task;

public class ToDo extends Task {
    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }
    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String toStorageFormat() {
        return String.format("T;%b;%s", this.isDone, this.description);
    }
}
