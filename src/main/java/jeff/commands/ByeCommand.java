package jeff.commands;

import jeff.Command;
import jeff.Printer;

/**
 * Represents a command to exit the application
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command by printing a farewell message.
     */
    @Override
    public void execute() {
        Printer.printBye();
    }

    /**
     * Checks if the program should exit.
     *
     * @return {@code true} since we want the program to exit after execution of bye command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
