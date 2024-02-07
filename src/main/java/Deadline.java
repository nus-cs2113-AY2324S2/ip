public class Deadline extends Task {

    protected String deadlineDate;

    public Deadline(String description) {
        super(description);
        taskType = "D";
        int indexOfDivider = description.indexOf("/");

        if (indexOfDivider == -1) {
            this.description = description;
            setDeadlineDate(null);
        } else {
            String endDate = description.substring(indexOfDivider + 4);
            String descriptionWithoutDate = description.substring(0, (indexOfDivider - 1)).replace("deadline", "");

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
