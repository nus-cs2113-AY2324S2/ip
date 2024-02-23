package daisy.task;

public class Todo extends Task{
    public Todo(String taskName) {
        super(taskName);
    }

    public String toString() {
        return String.format("[T][%s] %s", (this.isDone)? "X":" ", this.taskName);
    }

    public String save() {
        return String.format("T,%s,%s", (this.isDone)? "1":"0", this.taskName);
    }

}
