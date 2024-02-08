package task;

public class Event extends Task {
    public Event(String eventDescription, int taskNumber) {
        super(eventDescription, taskNumber);
        this.setTaskType(TaskType.EVENT);
    }
}
