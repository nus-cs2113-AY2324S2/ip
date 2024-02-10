package nyanbot.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getDescription() {
        return "TODO: " + description;
    }
}
