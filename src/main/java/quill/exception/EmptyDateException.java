package quill.exception;

public class EmptyDateException extends QuillException {
    public EmptyDateException (String type) {
        System.out.println("No date after " + type + "? Seriously?");
    }
}
