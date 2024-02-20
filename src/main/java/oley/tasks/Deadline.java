package oley.tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description) throws TimingNotFoundException {
        super(description);
        if (!description.contains("/by")) {
            throw new TimingNotFoundException();
        }
        int dividerIndex = description.indexOf("/");
        String taskName = description.substring(0, dividerIndex - 1);
        setTaskName(taskName);
        by = description.substring(dividerIndex + 4);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String format() {
        if (checkDone()) {
            return "1deadline " + getTaskName() + " /by " + by;
        } else {
            return "0deadline " + getTaskName() + " /by " + by;
        }
    }
}
