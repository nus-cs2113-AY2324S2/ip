public class Todo extends Task {
    private static int taskStartIndex = 5;

    public Todo(String line) {
        super(line.substring(taskStartIndex));
    }
}
