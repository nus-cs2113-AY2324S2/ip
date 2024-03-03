import java.time.LocalDateTime;

public class Deadline extends Task{
    protected LocalDateTime end;
    protected String taskType;

    public Deadline(String task, LocalDateTime end, String taskType) {
        super(task);
        this.end = end;
        this.taskType = taskType;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public LocalDateTime getEnd() {
        return end;
    }
}
