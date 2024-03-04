package jake.task;
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by, boolean isDateFormatted) {
        super(description.substring(9));
        this.by = isDateFormatted ? by : convertDateTime(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
