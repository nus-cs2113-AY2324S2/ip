import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String task,String from,String to)
    {
        super(task);
        this.from = TimeHandler.parseDateTime(from);
        this.to = TimeHandler.parseDateTime(to);
    }
    public Event(String task,String from,String to,boolean isComplete)
    {
        super(task,isComplete);
        this.from = TimeHandler.parseDateTime(from);
        this.to =  TimeHandler.parseDateTime(to);
    }
    public String toString()
    {
        String formattedFrom = TimeHandler.formatDateTime(this.from);
        String formattedTo = TimeHandler.formatDateTime(this.to);
        return "[E]" + super.toString() + " (from: "+formattedFrom+" to: "+formattedTo+")";
    }

    protected String getTaskType()
    {
        return "E";
    }

    public String toFileFormat()
    {
        String formattedFrom = TimeHandler.formatDateTimeSave(this.from);
        String formattedTo = TimeHandler.formatDateTimeSave(this.to);
        return String.format("%s | %d | %s | %s | %s", getTaskType(), this.isComplete() ? 1 : 0, this.getTask(),formattedFrom,formattedTo);
    }
}

