package yuki.task;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
        // set marker as [T] for printing.
        this.taskType = "[T]";
    }

}
