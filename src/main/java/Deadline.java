public class Deadline extends Task {

    protected String deadlineDate;

    @Override
    public String saveTaskRepresentation() {
        return "D|" + super.saveTaskRepresentation()  + "|" + deadlineDate; // Prefix with "D" to indicate Deadline
    }

    public Deadline(String description) {
        super(description);
        taskType = "D";
        int indexOfDivider = description.indexOf("by:");

        if (indexOfDivider == -1) {
            this.description = description;
            setDeadlineDate(null);
        } else {
            String endDate = description.substring(indexOfDivider + 3);
            String descriptionWithoutDate = description.substring(0, (indexOfDivider)).replace("deadline", "");

            setDeadlineDate(endDate);
            this.description = descriptionWithoutDate + " (by: " + getDeadlineDate() + ")";
        }
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
}
