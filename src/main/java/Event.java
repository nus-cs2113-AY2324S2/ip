public class Event extends Task {

    protected String startingDate;
    protected String endingDate;

    public Event(String description) {
        super(description);
        typeOfTask = "E";

        int dividerIndexFrom = description.indexOf("/");
        int dividerIndexTo = description.lastIndexOf("/");

        setStartingDate(description.substring(dividerIndexFrom + 6, dividerIndexTo - 1));
        setEndingDate(description.substring(dividerIndexTo + 4));

        if (dividerIndexFrom == -1) {
            setStartingDate(null);
        }

        else if (dividerIndexTo == -1) {
            setEndingDate(null);
        }

        String descriptionWithoutDate = description.substring(0, (dividerIndexFrom - 1)).replace("event", "");

        this.description = descriptionWithoutDate + " (from: " + getStartingDate() + " to: " + getEndingDate() + ")";
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }
}
