package task;

public class Deadline extends Task {
    public Deadline(String eventDescription, int taskNumber) {
        super(eventDescription, taskNumber);
        this.setTaskType(TaskType.DEADLINE);
    }
}
