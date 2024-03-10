package exceptions;

public class EmptyTaskDescription extends Exception {
    public EmptyTaskDescription() {
        super("The description of a task cannot be empty.");
    }
}
