package yuki.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        // set marker as [T] for printing.
        this.taskType = "[T]";
    }

}
