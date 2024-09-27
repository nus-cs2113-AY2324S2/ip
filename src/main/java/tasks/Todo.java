package tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = 'T';
    }
    @Override
    public char getType() {
        return type;
    }
}
