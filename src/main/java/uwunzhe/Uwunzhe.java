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
     * 
     * @param None
     * @return None
     */
    public static boolean init() {
        try {
            inputHandler = new Parser();
            storageHandler = new Storage(taskList);
            return true; // init successful
        } catch (UwunzheException e) {
            UwunzheException.printException(e);
            return false; // init failed
        }
    }

    /**
     * The main loop of the bot, handles user input.
     * 
     * @param None
     * @return None
     */
    public static void loop() {
        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = ui.getInput();
                Command c = inputHandler.parseInput(input);
                c.execute(taskList, ui, storageHandler);
                isRunning = !(c.isExit());

            } catch (UwunzheException e) {
                UwunzheException.printException(e);
            }
        }

        // Close the scanner at the end of the program
        ui.closeScanner();
    }

    /**
     * The main method of the bot.
     * 
     * @param args
     */
    public static void main(String[] args) {
        ui.printInitMsg();

        boolean isInit = init();
        if (isInit) {
            // Start the main loop if init is successful
            loop();
        }

        ui.printExitMsg();
    }
}
