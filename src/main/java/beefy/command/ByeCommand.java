package beefy.command;

public class ByeCommand implements Command {
    @Override
    public void execute() {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
