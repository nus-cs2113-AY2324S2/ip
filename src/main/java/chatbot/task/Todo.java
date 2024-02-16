package chatbot.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public String getTypeDisplay() {
        return "[T]";
    }
    public String getData() {
        return this.getTypeDisplay() + this.getMarkDisplay() + " " + this.getDescription();
    }
}
