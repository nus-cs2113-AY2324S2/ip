public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        //parent class sets String description and bool isDone
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public static Deadline extractTaskAndDueDate (String input) {
        Deadline taskAndDeadlineString = new Deadline("random","random");
        //splits string according to /by keyword
        String[] parts = input.split("/by");
        //extracts task portion from input, after deadline keyword
        taskAndDeadlineString.description = parts[0].substring("deadline".length()).trim();
        //extracts deadline portion from input
        taskAndDeadlineString.by = parts[1].trim();
        return taskAndDeadlineString;
    }

    public String getBy() {
        return by;
    }
}
