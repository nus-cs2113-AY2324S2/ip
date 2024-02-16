package exception;

public class InvalidTaskException extends Exception implements AnonbotExceptionHandler {
    private String taskDescription;

    public InvalidTaskException(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    protected String getTaskDescription() {
        return this.taskDescription;
    }

    @Override
    public void printErrorMessage() {
        System.out.println("Invalid Task: Task Description is empty");
    }
}
