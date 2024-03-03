package ava.exception;

/**
 * Signals that the user does not specify the name of the task when adding a task.
 */
public class EmptyTaskNameException extends Exception {
    public EmptyTaskNameException() {
        super("Empty task name.");
    }
}
