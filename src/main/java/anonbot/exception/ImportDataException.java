package anonbot.exception;

/**
 * Exception class relating to issues with reading data from a save file.
 */
public class ImportDataException extends DataException {
    public ImportDataException(String importErrorDescription) {
        super(importErrorDescription);
    }
}
