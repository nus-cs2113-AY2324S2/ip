package bossman.command;

import bossman.exceptions.BossManExceptions;
import bossman.exceptions.commandexceptions.UnknownCommandException;

/**
 * ByeCommand represents the command to exit the application.
 * It implements the Command interface.
 */
public class ByeCommand implements Command {

    /**
     * Constructs a ByeCommand.
     *
     * @param commandArgs the parameters provided by the user (should be empty)
     * @throws BossManExceptions if the commandArgs is not empty, indicating an unknown command
     */
    public ByeCommand(String commandArgs) throws BossManExceptions {
        if (!commandArgs.isEmpty()) {
            throw new UnknownCommandException("Unknown command");
        }
    }

    /**
     * Executes the ByeCommand.
     * It does nothing as the command only indicates to exit the application.
     */
    @Override
    public void execute() {
        return;
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true, indicating that this command is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
