package chatbot.commands;

import chatbot.ChatbotException;

public interface Command {
    void execute(boolean isReading) throws ChatbotException;
}
