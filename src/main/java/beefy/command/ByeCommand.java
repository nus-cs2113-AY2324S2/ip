package beefy.command;

import beefy.BeefyException;

public class ByeCommand implements Command {

    public ByeCommand(String userParams) throws BeefyException {
        if (!userParams.isEmpty()) {
            throw new BeefyException("Use format:" + System.lineSeparator() + "bye");
        }
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
