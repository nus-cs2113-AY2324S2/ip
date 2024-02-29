package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
    }
        

    @Override
    public String toString() {
        String isDoneIcon = super.isDone ? "X" : " ";
        return "[" + type + "][" + isDoneIcon + "] " + super.description;
    }
}
