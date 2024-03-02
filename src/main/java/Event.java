import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected String taskType;

    public Event(String task, LocalDateTime start, LocalDateTime end, String taskType) {
        super(task);
        this.start = start;
        this.end = end;
        this.taskType = taskType;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public LocalDateTime getStart() {
        return start;
    }

    @Override
    public LocalDateTime getEnd() {
        return end;
    }
}
