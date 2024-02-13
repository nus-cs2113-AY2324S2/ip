public class Event extends Task {
    private final String startTime;
    private final String endTime;
    public Event(String description) {
        super(description);
        String[] descriptionList = description.split("/", 3);
        this.setDescription(descriptionList[0]);
        this.startTime = descriptionList[1].split("from ")[1];
        this.endTime = descriptionList[2].split("to ")[1];
    }
    public String getTypeDisplay() {
        return "[E]";
    }

    public String getData() {
        return this.getTypeDisplay() + this.getMarkDisplay() + " " + this.getDescription()
                + "(from: " + this.startTime + ", to: " + this.endTime + ")";
    }
}
