public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        String realDescription = description.split("/from ")[0];
        return realDescription;
    }

    @Override
    public String getTimespan() {
        String startTime = description.split("/from ")[1].split(" /")[0];
        String endTime = description.split("/to ")[1];
        String timeSpan = "(from: " + startTime + " to: " + endTime + ")";
        return timeSpan;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }
}
