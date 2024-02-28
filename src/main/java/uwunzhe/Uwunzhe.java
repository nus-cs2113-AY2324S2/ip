package uwunzhe;

import uwunzhe.util.Ui;
import uwunzhe.util.TaskList;
import uwunzhe.exceptions.UwunzheException;
import uwunzhe.handler.Parser;
import uwunzhe.handler.Storage;
import uwunzhe.commands.Command;

public class Uwunzhe {
    private static final TaskList taskList = new TaskList();
    private static Parser inputHandler;
    private static Storage storageHandler;
    private static final Ui ui = new Ui();

    /**
     * Initializes the necessary handlers.
     */
    public static void init() {
        try {
            inputHandler = new Parser();
            storageHandler = new Storage(taskList);
            // Ignores invalid formats in the storage file.
        } catch (UwunzheException e) {
            UwunzheException.printException(e);
        }
    }

    /**
     * The main loop of the bot.
     */
    public static void loop() {
        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = ui.getInput();
                Command c = inputHandler.parseInput(input);
                c.execute(taskList, storageHandler);
                isRunning = !(c.isExit());

            } catch (UwunzheException e) {
                UwunzheException.printException(e);
            }
        }

        // Close the scanner at the end of the program
        ui.closeScanner();
    }

    /**
     * Main method of the bot.
     * 
     * @param args
     */
    public static void main(String[] args) {
        init();
        ui.printInitMsg();
        loop();
        ui.printExitMsg();
    }
}
