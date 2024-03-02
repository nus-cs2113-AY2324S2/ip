package chatbot.commands;

import chatbot.ChatbotException;

/**
 * Represents a default command.
 */
public interface Command {
    void execute(boolean isReading) throws ChatbotException;
}
