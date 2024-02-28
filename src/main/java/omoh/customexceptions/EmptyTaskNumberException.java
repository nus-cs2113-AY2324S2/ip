package omoh.customexceptions;

public class EmptyTaskNumberException extends Exception {
    public EmptyTaskNumberException() {
        System.out.println("Please enter a number after mark or unmark or delete " +
                "Example: mark 1");
    }
}
