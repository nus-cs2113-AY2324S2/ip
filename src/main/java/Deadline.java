public class Deadline extends Task {

    protected String by;

    public Deadline(String description) {
        super(description);
        int dividerIndex = description.indexOf("/");
        String taskName = description.substring(0, dividerIndex - 1);
        setTaskName(taskName);
        by = description.substring(dividerIndex + 4);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
