package uwunzhe.exceptions;

public enum ExceptionMessages {
    UNABLE_TO_FIND_TASK ("Huhhhhhhh? I cannot find!"),
    UNABLE_TO_CONVERT_TYPE ("Something something not adding up..."),
    UNEXPECTED_EXTRA_COMMAND ("Extra... Value?!"),
    EXPECTED_EXTRA_COMMAND ("No Value :("),
    EMPTY_LIST ("You KAIBAI-ing"),
    INVALID_COMMAND ("OH NO! I cannot understand!"),
    STORAGE_FILE_NOT_CREATED ("Storage file oopsies!"),
    STORAGE_FILE_NOT_READ ("Storage file oopsies!"),
    STORAGE_FILE_NOT_WRITTEN ("Storage file oopsies!"),
    INVALID_STORAGE_CONTENT ("EEK! Storage data!"),
    COMMAND_REPEATED ("No no no, not again..."),
    EXPECTED_EXTRA_DESCRIPTION ("ACKSHUALLY you are missing something..."),
    INVALID_DATE_FORMAT ("Semo date is that?");

    private final String message;

    /**
     * Constructor for ExceptionMessages.
     * 
     * @param message The message of the exception.
     */
    ExceptionMessages(String message) {
        this.message = message;
    }

    /**
     * Gets the message of the exception.
     * 
     * @return The message of the exception.
     */
    public String getMessage() {
        return this.message;
    }
}
