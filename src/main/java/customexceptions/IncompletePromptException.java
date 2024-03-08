package customexceptions;

public class IncompletePromptException extends Exception {
    protected boolean isPrintMessage;
    public boolean isPrintMessage() {
        return isPrintMessage;
    }
    public IncompletePromptException(boolean isPrintMessage) {
        this.isPrintMessage = isPrintMessage;
    }
}
