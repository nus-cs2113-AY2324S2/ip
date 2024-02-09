public class DefaultCommand implements Command {
    @Override
    public void execute() {
        Ui.printMessage("What Did you Say? I do not understand this command.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
