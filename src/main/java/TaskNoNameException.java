public class TaskNoNameException extends Exception {
    public TaskNoNameException() {
        super("ERROR: Task not given a name!");
    }
}
