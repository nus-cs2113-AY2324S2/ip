public class Deadline extends Task {

    protected String dateOfDeadline;

    public Deadline(String description) {
        super(description);
        typeOfTask = "D";
        int dividerIndex = description.indexOf("/");

        if (dividerIndex == -1) {
            this.description = description;
            setDateOfDeadline(null);
        }

        else {
            String endDate = description.substring(dividerIndex + 4);
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
}

