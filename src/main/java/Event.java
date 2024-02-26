public class Event extends Todo {
    protected String start;
    protected String end;

    //constructor for Event
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        type = "[E]";
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String formatTask() {

        return (type + status + description + "(from:" + start + "to:" + end + ")");
    }

    public String getWriteFormat() {
        return (type + status + description + "/from" + start + "/to" + end);
    }
}
