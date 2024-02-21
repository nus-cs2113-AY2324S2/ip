package anonbot.task;

public class Todo extends Task {
    public Todo(String eventDescription, int taskNumber) {
        super(eventDescription, taskNumber, TaskType.TODO);
    }
}
