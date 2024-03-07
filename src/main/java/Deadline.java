import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String task;
    private LocalDateTime by;
    public Deadline(String task, String by)
    {
        super(task);
        this.by = TimeHandler.parseDateTime(by);
    }
    public Deadline(String task, String by, boolean isComplete)
    {
        super(task,isComplete);
        this.by = TimeHandler.parseDateTime(by);
    }

    public String toString()
    {
        String formattedBy = TimeHandler.formatDateTime(this.by);
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }

    protected String getTaskType()
    {
        return "D";
    }
    public String toFileFormat() {
        String formattedBy = TimeHandler.formatDateTimeSave(this.by);
        return String.format("%s | %d | %s | %s", getTaskType(), this.isComplete() ? 1 : 0, this.getTask(),formattedBy);
    }
}
