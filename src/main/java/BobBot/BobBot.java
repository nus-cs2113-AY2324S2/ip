package BobBot;

import BobBot.parser.Parser;
import BobBot.storage.Storage;
import BobBot.ui.Ui;

public class BobBot {

    public BobBot() {

    }

    public void run() {
        Ui.greet();
        Storage.loadFile();
        Parser.runTaskManager();
        Ui.bidFarewell();
    }

    public static void main(String[] args) {
        new BobBot().run();
    }

}
