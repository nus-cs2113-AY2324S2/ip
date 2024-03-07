package BobBot;

import BobBot.parser.Parser;
import BobBot.storage.Storage;
import BobBot.ui.Ui;

/**
 * Implements a Task Managing CLI application that takes task information in 
 * various formats.
 * 
 * @author NicholasTanYY
 * @since January 2024
 * @version 1.0
 */
public class BobBot {

    public BobBot() {

    }

    /**
     * Runs the Task Manager application.
     */
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
