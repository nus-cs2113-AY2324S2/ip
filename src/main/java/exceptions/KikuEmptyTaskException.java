package exceptions;

public class KikuEmptyTaskException extends KikuException {
    private static final String HORIZONTAL = "____________________________________________________________";
    public KikuEmptyTaskException() {
        super("Oh no! The task description is empty. \n" + HORIZONTAL);
    }
}
