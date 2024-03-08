package customexceptions;

/**
 * Indicates that prompt is incomplete and needs additional information.
 */
public class IncompletePromptException extends Exception {
    protected boolean isPrintMessage;
    public boolean isPrintMessage() {
        return isPrintMessage;
    }

    /**
     * Constructs exception object and specifies print message boolean flag that indicates
     * error message being printed or not.
     *
     * @param isPrintMessage True if error message is to be printed, false otherwise.
     */
    public IncompletePromptException(boolean isPrintMessage) {
        this.isPrintMessage = isPrintMessage;
    }
}
