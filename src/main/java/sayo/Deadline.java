package sayo;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }

    public static Task fromFileFormat(String fileFormat) {
        // Assuming fileFormat is in the correct format for Deadline
        String[] parts = fileFormat.split(" \\| ");
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String by = parts[3].trim();

        Deadline deadline = new Deadline(description, by);
        if (isDone) {
            deadline.markAsDone();
        }
        return deadline;
    }
}
