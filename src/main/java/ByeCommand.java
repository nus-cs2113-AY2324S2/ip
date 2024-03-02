public class ByeCommand extends Command{
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printByeMessage();
        return false;
    }
}
