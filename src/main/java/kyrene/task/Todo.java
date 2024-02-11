package kyrene.task;

public class Todo extends Task{

    public Todo() {
        super(null);
    }

    public Todo(String taskName) {
        super(taskName);
        taskType = "T";
    }

}
