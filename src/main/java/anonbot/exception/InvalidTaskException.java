package anonbot.exception;

public class InvalidTaskException extends Exception implements AnonbotExceptionHandler {
    private String taskDescription;

    public InvalidTaskException(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void printErrorMessage() {
        System.out.println(taskDescription);
    }
}
