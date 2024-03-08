package anonbot.exception;

/**
 * Exception class relating to issues with saving data to a file.
 */
public class ExportDataException extends DataException {
    public ExportDataException(String exportErrorDescription) {
        super(exportErrorDescription);
    }
}
