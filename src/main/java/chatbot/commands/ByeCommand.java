package chatbot.commands;

/**
 * Represents a command to exit the chatbot.
 */
public class ByeCommand implements Command {
    /**
     * Executes the command.
     *
     * @param isReading If the chatbot is reading a file, during which it will not display confirmation text.
     */
    @Override
    public void execute(boolean isReading) {
    }
}
