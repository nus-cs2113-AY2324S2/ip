package InvalidInputExceptions;

public class InvalidDeadlineException extends InvalidInputException {
    public InvalidDeadlineException(String errorMessage) {
        super(errorMessage);
        System.out.println("\tDeadline format: deadline TASK_NAME /by DEADLINE");
        System.out.println("\tDEADLINE should be fully numerical and of the format dd/MM/yyyy HHmm");
    }
}
