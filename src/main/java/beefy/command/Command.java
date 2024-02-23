package beefy.command;

import beefy.BeefyException;

import java.io.IOException;

public interface Command {
    void execute() throws BeefyException, IOException;
    boolean isExit();
}
