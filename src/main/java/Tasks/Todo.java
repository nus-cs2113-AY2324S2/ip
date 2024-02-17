package Tasks;

// class not very necesary now but just future proofing if I want to change the class Task
public class Todo extends Task {
    public Todo(String content, boolean isDone) {
        super(content, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
