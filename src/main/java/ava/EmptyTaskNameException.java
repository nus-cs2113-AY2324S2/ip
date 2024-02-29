package ava;

public class EmptyTaskNameException extends Exception {
    public EmptyTaskNameException() {
        super("Empty task name.");
    }
}
