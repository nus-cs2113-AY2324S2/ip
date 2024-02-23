public class Deadline extends Task {
    protected String dateOfDeadline;

    public Deadline(String description) {
        super(description);
        typeOfTask = "D";
        int dividerIndex = description.indexOf("by");

        if (dividerIndex == -1) {
            this.description = description;
            setDateOfDeadline(null);
        }

        else {
            String endDate = description.substring(dividerIndex + 3);
            String descriptionWithoutDate = description.substring(0, (dividerIndex - 1)).replace("deadline", "");
            setDateOfDeadline(endDate);
            this.description = descriptionWithoutDate + " (by: " + getDateOfDeadline() + ")";
        }
    }

    public String getDateOfDeadline() {
        return dateOfDeadline;
    }

    public void setDateOfDeadline(String dateOfDeadline) {
        this.dateOfDeadline = dateOfDeadline;
    }

    @Override
    public String toFileString() {
        return "D|" + super.toFileString() + "|" + dateOfDeadline; // Prefix with "D" to indicate Deadline
    }
}

