package Casper;

public class ExitCommand extends Command{
    private final String commandType;
    public ExitCommand(boolean isRunning, String commandType){
        super(isRunning);
        this.commandType = commandType;
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) {
        if(commandType.equals("exit")){
            ui.wrapEchoMessage("Alright, see you around!");
        }else{
            ui.wrapEchoMessage("[ERROR]: An error has occurred, please try again!");
        }
    }
}
