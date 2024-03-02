package chatbot.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public String getTypeDisplay() {
        return "[T]";
    }
    public String getTaskName() {
        return "todo";
    }
    public String getData() {
        return this.getTypeDisplay() + this.getMarkDisplay() + " " + this.getDescription();
    }
    public void printData() {
        System.out.println(this.getTypeDisplay() + this.getMarkDisplay() + " " + this.getDescription());
    }
}
