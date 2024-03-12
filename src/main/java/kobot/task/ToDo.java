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

    /**
     * Converts todo to format used for local storage.
     *
     * @return Formatted string of the to-do.
     */
    public String toStorageFormat() {
        return String.format("T;%b;%s", this.isDone, this.description);
    }
}
