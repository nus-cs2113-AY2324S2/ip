package bossman.command;

import bossman.exceptions.BossManExceptions;
import java.io.IOException;

public interface Command {
    void execute() throws BossManExceptions, IOException;
    boolean isExit();
}
