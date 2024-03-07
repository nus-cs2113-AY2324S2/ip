package anonbot.exception;

public class InitialisationException extends Exception implements AnonbotExceptionHandler {
    private String errorDescription;

    public InitialisationException(String errorDescription) {
        setErrorDescription(errorDescription);
    }

    private void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    private String getErrorDescription() {
        return this.errorDescription;
    }

    @Override
    public void printErrorMessage() {
        System.out.println(getErrorDescription());
    }
}
