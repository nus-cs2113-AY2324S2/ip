public class KikuEmptyTaskException extends KikuException {
    public KikuEmptyTaskException() {
        super("Oh no! The task description is empty");
    }
}
