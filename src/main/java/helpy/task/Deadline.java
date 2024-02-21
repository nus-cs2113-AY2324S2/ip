package helpy.task;

public class Deadline extends Task{
    protected String dueDate;

    public Deadline(String command) {
        super();
        String description = command.replace("deadline", "").trim();
        String[] details = description.split("/by");
        this.taskName = details[0].trim();
        this.dueDate = details[1].trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dueDate + ")";
    }
}
