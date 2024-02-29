package Casper;

/**
 * An implementation of <code>Command</code> focusing on commands that terminate the program.
 */
public class ExitCommand extends Command{
    private final String commandType;
    public ExitCommand(boolean isRunning, String commandType){
        super(isRunning);
        this.commandType = commandType;
    }

    /**
     * Executes the termination of the program, both intentionally or otherwise.
     *
     * @param ui An instance of <code>Ui</code>.
     * @param tasks An instance of <code>TaskList</code>.
     * @param storage An instance of <code>Storage</code>.
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        if(commandType.equals("exit")){
            ui.wrapEchoMessage("Alright, see you around!");
        }else{
            ui.wrapEchoMessage("[ERROR]: An error has occurred, please try again!");
        }
    }
}
