package jason.tasks;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    public static Task parseFromFile(String data) {
        boolean isDone = data.charAt(4) == 'X'; // Check if the task is marked as done.
        String byPart = data.substring(data.lastIndexOf("(by: ") + 4, data.length() - 1).trim();
        String description = data.substring(6, data.lastIndexOf(" (by: ")).trim();

        Deadline task = new Deadline(description, byPart);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

}