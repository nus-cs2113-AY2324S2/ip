/**
 * Abstract class for all commands to inherit
 */
public abstract class Command {
    /**
     *
     * @param taskList temporary TaskList used within the code
     * @param ui ui class for printing
     * @param storage Storage class to save task list to hardware
     * @return Returns true if program should continue after Command, false if program should terminate i.e. ByeCommand
     * @throws OGFException used for all exceptions
     */
    public abstract boolean execute(TaskList taskList, Ui ui, Storage storage) throws OGFException; //todo add params
}
