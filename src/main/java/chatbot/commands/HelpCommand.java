package chatbot.commands;

import chatbot.ChatbotException;
import chatbot.ui.UI;

public class HelpCommand implements Command {

    @Override
    public void execute(boolean isReading) throws ChatbotException {
        UI.printHelp();
    }
}
