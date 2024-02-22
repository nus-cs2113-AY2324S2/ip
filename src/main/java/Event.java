import java.io.FileWriter;
import java.io.IOException;

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
    public String getCSV () {
        return "E" + "," + super.getCSV() + "," + from + "," + to;
    }

    public String toString () {
        return "[E]" + super.toString() + " (from: " + from + " " + "to: " + to + ")";
    }
}
