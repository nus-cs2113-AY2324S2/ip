public class Todo extends Task{
    public Todo(String taskName) {
        super(taskName);
    }

    public String toString() {
        return String.format("[T][%s] %s", (this.isDone)? "X":" ", this.taskName);
    }

}
