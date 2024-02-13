public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super("\n" + "Error Occurred: " + "\n" + message + "\n");
    }
}
