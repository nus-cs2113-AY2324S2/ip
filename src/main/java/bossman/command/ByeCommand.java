package bossman.command;

import bossman.exceptions.BossManExceptions;
import bossman.exceptions.commandexceptions.UnknownCommandException;

public class ByeCommand implements Command {

    public ByeCommand(String userParams) throws BossManExceptions {
        if (!userParams.isEmpty()) {
            throw new UnknownCommandException("Unknown command");
        }
    }

    @Override
    public void execute() {
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
