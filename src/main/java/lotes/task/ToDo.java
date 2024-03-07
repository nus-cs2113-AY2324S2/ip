package lotes.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return String.format("[T]%s %s",
                this.getStatusIcon(), this.description);
    }
}
