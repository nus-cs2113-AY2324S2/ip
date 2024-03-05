package blue.exception;

public class IllegalIndexException extends IllegalInputException {

    @Override
    public String getMessage() {
        return "Please specify a task index";
    }
}
