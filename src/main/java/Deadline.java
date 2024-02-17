public class Deadline extends Task{

    private String dueTime;
    Deadline(String taskName, String dueTime) {
        super(taskName);
        this.dueTime = dueTime;
    }

    @Override
    String getSummary() {
        if (completed) {
            return "[D][X] " + taskName + " (by: " + dueTime + ")";
        } else {
            return "[D][ ] " + taskName + " (by: " + dueTime + ")";
        }
    }
}
