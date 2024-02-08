public class Event extends Task {

    public String start;
    public String end;
    //public static String taskType = "E";

    public Event(String description, String startTime, String endTime) {
        super(description);
        start = startTime;
        end = endTime;
    }

    @Override
    public String getDescription() {
        return description + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String taskString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
