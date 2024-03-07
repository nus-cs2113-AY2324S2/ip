package anonbot.exception;

public class DataException extends Exception implements AnonbotExceptionHandler {
    private String dataErrorDescription;

    public DataException(String dataErrorDescription) {
        setDataErrorDescription(dataErrorDescription);
    }

    private void setDataErrorDescription(String dataErrorDescription) {
        this.dataErrorDescription = dataErrorDescription;
    }

    private String getDataErrorDescription() {
        return this.dataErrorDescription;
    }

    @Override
    public void printErrorMessage() {
        System.out.println(getDataErrorDescription());
    }
}
