public class Event extends Task {

    protected String startDate;
    protected String endDate;

    @Override
    public String saveTaskRepresentation() {
        return "E|" + super.saveTaskRepresentation()+ "|" + startDate.trim() + "|" + endDate.trim(); // Prefix with "E" to indicate Event
    }

    public Event(String description) {
        super(description);
        taskType = "E";

        int indexOfFromDivider = description.indexOf("from:");
        int indexOfToDivider = description.indexOf("to:");

        setStartDate(description.substring(indexOfFromDivider + 5, indexOfToDivider - 1));
        setEndDate(description.substring(indexOfToDivider + 3));

        if (indexOfFromDivider == -1) {
            setStartDate(null);
        } else if (indexOfToDivider == -1) {
            setEndDate(null);
        }

        String descriptionWithoutDate = description.substring(0, (indexOfFromDivider - 1)).replace("event", "");

        this.description = descriptionWithoutDate + " (from: " + getStartDate() + " to: " + getEndDate() + ")";
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
