package beefy.command;

import beefy.BeefyException;

public interface Command {
    void execute() throws BeefyException;
    boolean isExit();
}
