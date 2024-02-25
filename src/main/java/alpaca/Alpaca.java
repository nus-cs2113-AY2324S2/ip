package alpaca;

import alpaca.file.FileReader;
import alpaca.logic.LogicManager;
import alpaca.ui.Ui;
import alpaca.parser.Parser;

public class Alpaca {
    public static void startConversation() {
        Ui ui = new Ui();
        LogicManager logicManager = new LogicManager(ui);
        Parser Parser = new Parser();

        ui.printGreeting();
        Parser.listenForInput(logicManager);
    }

    public static void main(String[] args) {
        startConversation();
    }
}
