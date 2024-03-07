package anonbot.exception;

public class ImportDataException extends DataException {
    public ImportDataException(String importErrorDescription) {
        super(importErrorDescription);
    }
}
