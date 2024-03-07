
/**
 * Represents an event with name, start and end time
 */
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

    public Event (String name, String from, String to, boolean isDone) {
        this(name, from, to);
        this.isDone = isDone;
    }

    //Methods

    /**
     * @return a CS representation of an Event object.
     * For example, "E,0,Taylor Swift's The Eras Tour,7/3/2024 1600,7/3/2024 2000"
     * means the user has not attended (0) an event called "Taylor Swift's The Eras Tour"
     * which happens on 7/3/2024 from 1600 to 2000.
     */
    public String getCSV () {
        return "E" + "," + super.getCSV() + "," + from + "," + to;
    }

    /**
     * @return a String representation of an Event object
     */
    public String toString () {
        return "[E]" + super.toString() + " (from: " + from + " " + "to: " + to + ")";
    }
}
