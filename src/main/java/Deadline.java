public class Deadline extends Task {

    protected String dateTime;
    public Deadline(String description) {
        super(description);
        String[] givenDeadline = description.split("/by ", 2);
        this.description = givenDeadline.length >= 1 ? givenDeadline[0] : "";
        this.dateTime = givenDeadline.length > 1 ? givenDeadline[1] : "";
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + description + " (by: " + dateTime + ")";
    }



}
