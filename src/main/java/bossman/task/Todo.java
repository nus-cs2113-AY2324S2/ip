package bossman.task;
public class Todo extends Task {
    private final String TYPE_SYMBOL = "[T]";
    public Todo(String task, boolean isMark) {
        super(task, isMark);
    }

    @Override
    public String getTypeSymbol() {
        return TYPE_SYMBOL;
    }

    @Override
    public String formatForSave() {
        return "T" + "," + isMark + "," + DESCRIPTION ;
    }
}