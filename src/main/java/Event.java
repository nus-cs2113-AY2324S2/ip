public class Event extends Task {
    //Attributes
    protected String from;

    protected String to;

    //Constructors
    public Event (String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
        this.taskType = TaskType.EVENT;
    }

    //Methods
    public String toString () {
        return "[E]" + super.toString() + "(from: " + from + " " + "to: " + to + ")";
    }
}
