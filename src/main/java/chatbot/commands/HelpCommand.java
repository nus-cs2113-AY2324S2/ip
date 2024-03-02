package chatbot.commands;

import chatbot.ui.UI;

/**
 * Represents a command to display available commands.
 */
public class HelpCommand implements Command {

    /**
     * Executes the command to display available commands.
     *
     * @param isReading If the chatbot is reading a file, during which it will not display confirmation text.
     */
    @Override
    public void execute(boolean isReading) {
        UI.printHelp();
    }
}
