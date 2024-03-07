package anonbot.exception;

public class ImportDataException extends Exception implements AnonbotExceptionHandler {
    private String importErrorDescription;

    public ImportDataException(String errorDescription) {
        setImportErrorDescription(errorDescription);
    }

    private void setImportErrorDescription(String importErrorDescription) {
        this.importErrorDescription = importErrorDescription;
    }

    private String getImportErrorDescription() {
        return this.importErrorDescription;
    }

    @Override
    public void printErrorMessage() {
        System.out.println(getImportErrorDescription());
    }
}
