public class Todo extends Task {
    protected String type = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
    @Override
    public String toString(boolean isFormatCache) {
        return String.format("%s", super.toString(isFormatCache));
    }
}
