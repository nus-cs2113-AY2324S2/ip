package beefy.command;

import beefy.BeefyException;

/**
 * Represents a command to exit the Duke chatbot.
 */
public class ByeCommand implements Command {

    /**
     * Constructs a new ByeCommand object with user input.
     *
     * @param userParams User input of the deadline command.
     * @throws BeefyException if there is any formatting issues.
     */
    public ByeCommand(String userParams) throws BeefyException {
        if (!userParams.isEmpty()) {
            throw new BeefyException("Use format:" + System.lineSeparator() + "bye");
        }
    }

    /**
     * Executes the exit command, displaying the exit message and exiting Beefy chatbot.
     */
    @Override
    public void execute() {
        return;
    }

    /**
     * Indicates whether this is an exit command.
     * Returns true since this is an exit command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
