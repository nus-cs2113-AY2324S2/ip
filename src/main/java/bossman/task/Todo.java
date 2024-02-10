package bossman.task;
public class Todo extends Task {
    private final String TYPE_SYMBOL = "[T]";
    public Todo(String task) {
        super(task);
    }

    @Override
    public String getTypeSymbol() {
        return TYPE_SYMBOL;
    }
}