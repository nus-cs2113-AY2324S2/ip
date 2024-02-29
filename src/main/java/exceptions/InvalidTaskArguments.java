package exceptions;

public class InvalidTaskArguments extends Exception {
    public InvalidTaskArguments(String sampleInput) {
        super("The task description is invalid. Please follow this format: "
                + System.lineSeparator()
                + sampleInput);
    }
}
