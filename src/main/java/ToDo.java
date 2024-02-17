public class ToDo extends Task {
    ToDo(String taskName) {
        super(taskName);
    }

    @Override
    String getSummary() {
        if (completed) {
            return "[T][X] " + taskName;
        } else {
            return "[T][ ] " + taskName;
        }
    }
}
