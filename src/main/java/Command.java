// abstract command class for execute method
public abstract class Command {
    public abstract boolean execute(TaskList taskList, Ui ui, Storage storage) throws OGFException; //todo add params
}
