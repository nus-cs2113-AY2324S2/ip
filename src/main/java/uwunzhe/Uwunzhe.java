package uwunzhe;

import uwunzhe.util.Printer;
import uwunzhe.util.UserInput;
import uwunzhe.util.TaskList;
import uwunzhe.exceptions.UwunzheException;
import uwunzhe.handler.InputHandler;
import uwunzhe.handler.StorageHandler;

public class Uwunzhe {
    private static final TaskList taskList = new TaskList();
    private static InputHandler inputHandler;
    private static StorageHandler storageHandler;

    /**
     * Initializes the necessary handlers.
     * 
     * @param None
     * @return None
     */
    public static boolean init() {
        try {
            inputHandler = new InputHandler(taskList);
            storageHandler = new StorageHandler(taskList);
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
                String input = UserInput.getInput();
                
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
        UserInput.closeScanner();
    }

    /**
     * The main method of the bot.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Printer.printInitMsg();

        boolean isInit = init();
        if (isInit) {
            // Start the main loop if init is successful
            loop();
        }

        Printer.printExitMsg();
    }
}
