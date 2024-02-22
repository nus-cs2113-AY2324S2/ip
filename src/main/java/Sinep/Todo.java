package Sinep;

public class Todo extends Task {
    public Todo(String description) {

        super(description);
        this.description = description.trim();
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + description;
    }
}
