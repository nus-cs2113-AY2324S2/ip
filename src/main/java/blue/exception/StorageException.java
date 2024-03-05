package blue.exception;

public class StorageException extends BlueException {

    @Override
    public String getMessage() {
        return "Failed to save tasks!";
    }
}
