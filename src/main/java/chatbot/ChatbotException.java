package chatbot;

import chatbot.ui.UI;

/**
 * Represents an exception for the chatbot.
 */
public class ChatbotException extends Exception {
    private final String description;

    /**
     * Constructs a ChatbotException object.
     *
     * @param description The error message of the exception.
     */
    public ChatbotException(String description) {
        this.description = description;
    }
    public void printDescription() {
        System.out.println(description);
        UI.printSeparator();
    }
}
