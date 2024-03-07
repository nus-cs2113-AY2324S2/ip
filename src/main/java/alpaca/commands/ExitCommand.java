package alpaca.commands;

import alpaca.ui.Ui;

public class ExitCommand extends AlpacaCommand{

    @Override
    public void execute () {
        Ui.printGoodbye();
        System.exit(0);
    }
}
