//Todo class to represent tasks without any date/time attached
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTypeSymbol() {
        return "T";
    }
    @Override
    public String toFileString() {
        return super.toFileString();
    }

    public static Todo fromFileFormat(String fileLine) {
        String[] parts = fileLine.split(" \\| ");

        if (parts.length < 3) {
            // Not enough data to create a valid Todo task
            return null;
        }

        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Todo todoTask = new Todo(description);
        todoTask.setDone(isDone);

        return todoTask;
    }
}

