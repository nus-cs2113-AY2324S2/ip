package Alexis.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    protected static ToDo getToDo(String input) {
        return new ToDo(input.trim());
    }
    @Override
    public String toString() {
        return String.format("[T]%s\n", super.toString());
    }
}
