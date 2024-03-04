package OGFCommand;

import OGFCore.OGFException;
import OGFCore.Storage;
import OGFCore.TaskList;
import OGFCore.Ui;

/**
 * Abstract class for all commands to inherit
 */
public abstract class Command {
    /**
     *
     * @param taskList temporary OGFCore.TaskList used within the code
     * @param ui ui class for printing
     * @param storage OGFCore.Storage class to save task list to hardware
     * @return Returns true if program should continue after OGFCommand.Command, false if program should terminate i.e. OGFCommand.ByeCommand
     * @throws OGFException used for all exceptions
     */
    public abstract boolean execute(TaskList taskList, Ui ui, Storage storage) throws OGFException;
}
