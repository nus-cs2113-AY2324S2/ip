package alpaca.commands;

import alpaca.ui.Ui;

/**
 * Represents a command to exit the program
 */
public class ExitCommand extends AlpacaCommand{

    /**
     * Exit the program
     */
    @Override
    public void execute () {
        Ui.printGoodbye();
        System.exit(0);
    }
}
