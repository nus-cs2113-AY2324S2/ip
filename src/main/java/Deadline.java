//Deadline class to represent tasks with a specific deadline
public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }



    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    @Override
    public String getTypeSymbol() {
        return "D";
    }

    @Override
    public String toFileString() {
        return String.format("%s | %s | %s | %s", super.toFileString(), isDone ? "1" : "0", description, by);
    }

    public static Deadline fromFileFormat(String fileLine) {
        String[] parts = fileLine.split(" \\| ");

        if (parts.length < 4) {
            // Not enough data to create a valid Deadline task
            return null;
        }

        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        String by = parts[3];

        Deadline deadlineTask = new Deadline(description, by);
        deadlineTask.setDone(isDone);

        return deadlineTask;
    }
}

