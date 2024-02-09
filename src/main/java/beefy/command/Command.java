package beefy.command;

public interface Command {
    void execute();
    boolean isExit();
}
