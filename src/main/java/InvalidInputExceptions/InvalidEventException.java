package InvalidInputExceptions;

public class InvalidEventException extends InvalidInputException {
    public InvalidEventException(String errorMessage) {
        super(errorMessage);
        System.out.println("\tEvent format: event TASK_NAME /from START_TIME /to END_TIME");
        System.out.println("\tSTART_TIME and END_TIME should be fully numerical and of the format dd/MM/yyyy HHmm");
    }
}