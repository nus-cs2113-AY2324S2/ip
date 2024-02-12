public class Event extends Task{
    protected String start;
    protected String end;
    protected String taskType;

    public Event(String task, String taskType, String start, String end) {
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
    public String getStart() {
        return start;
    }

    @Override
    public String getEnd() {
        return end;
    }
}
