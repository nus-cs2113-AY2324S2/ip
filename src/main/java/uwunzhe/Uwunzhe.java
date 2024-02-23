package uwunzhe;

import uwunzhe.util.Ui;
import uwunzhe.util.TaskList;
import uwunzhe.exceptions.UwunzheException;
import uwunzhe.handler.Parser;
import uwunzhe.handler.Storage;

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
            inputHandler = new Parser(taskList);
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
        boolean isListUpdated;

        while (isRunning) {
            try {
                isListUpdated = false;
                String input = ui.getInput();
                
                if (input.toLowerCase().equals("bye")) {
                    // Exit loop if input is "bye"
                    isRunning = false;
                    continue;
                }
                isListUpdated = inputHandler.parseInput(input);

                if (isListUpdated) {
                    // Save data to file if list is updated
                    storageHandler.saveData(taskList);
                }
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
