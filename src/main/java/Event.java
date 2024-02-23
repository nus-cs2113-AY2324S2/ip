public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        String realDescription = description.split("(?i)/from ")[0].split("(?i)event")[1];
        return realDescription;
    }

    @Override
    public String getTimespan() {
        String startTime = description.split("(?i)/from")[1].split("/")[0].trim();
        String endTime = description.split("(?i)/to")[1].trim();
        String timeSpan = "(from: " + startTime + " to: " + endTime + ")";
        return timeSpan;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return "E | " + (isDone ? "1 | " : "0 | ") + getDescription() + " |" + getTimespan();
    }
}
