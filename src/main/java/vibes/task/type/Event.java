package vibes.task.type;

public class Event extends Task{
    private final static String TASK_TYPE = "E";
    public static final String PRINT_FORMAT = "[E]%s (from: %s to: %s)";
    protected String from;
    protected String to;

    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String getTaskType(){
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return String.format(PRINT_FORMAT, super.toString(), from, to);
    }
}
