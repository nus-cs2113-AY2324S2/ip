public class Deadline extends Task{
    protected String end;
    protected String taskType;

    public Deadline(String task, String end, String taskType) {
        super(task);
        this.end = end;
        this.taskType = taskType;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String getEnd() {
        return end;
    }
}
