package kyrene.task;

public class Todo extends Task{

    public Todo() {
        super(null, false);
    }

    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
        taskType = "T";
    }

    @Override
    public String format() { return String.format("%b todo %s\n", isDone, taskName);}

}
