public class Event extends Task {

    protected String startDate;
    protected String endDate;

    public Event(String description) {
        super(description);
        taskType = "E";

        int indexOfFromDivider = description.indexOf("/");
        int indexOfToDivider = description.lastIndexOf("/");

        setStartDate(description.substring(indexOfFromDivider + 6, indexOfToDivider - 1));
        setEndDate(description.substring(indexOfToDivider + 4));

        if (indexOfFromDivider == -1) {
            setStartDate(null);
        } else if (indexOfToDivider == -1) {
            setEndDate(null);
        }

        String descriptionWithoutDate = description.substring(0, (indexOfFromDivider - 1)).replace("event", "");

        this.description = descriptionWithoutDate + " from: " + getStartDate() + " to: " + getEndDate() + ")";
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
