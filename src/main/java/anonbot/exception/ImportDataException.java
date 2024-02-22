package anonbot.exception;

public class ImportDataException extends Exception implements AnonbotExceptionHandler {
    private static String errorDescription;

    public ImportDataException(String errorDescription) {
        ImportDataException.errorDescription = errorDescription;
    }

    @Override
    public void printErrorMessage() {
        System.out.println(errorDescription);
    }
}
