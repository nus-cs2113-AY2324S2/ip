package uwunzhe;

import uwunzhe.util.Printer;
import uwunzhe.util.UserInput;
import uwunzhe.util.TaskList;
import uwunzhe.exceptions.UwunzheException;
import uwunzhe.handler.InputHandler;
import uwunzhe.handler.StorageHandler;

public class Uwunzhe {
    private static final TaskList taskList = new TaskList();

    /**
     * The main loop of the bot, handles user input.
     * 
     * @param None
     * @return None
     */
    public static void loop() {
        try {
            boolean isRunning = true;
            InputHandler inputHandler = new InputHandler(taskList);
            StorageHandler storageHandler = new StorageHandler();

            while (isRunning) {
                String input = UserInput.getInput();
                if (input.toLowerCase().equals("bye")) {
                    // Exit loop if input is "bye"
                    isRunning = false;
                    continue;
                }
                inputHandler.praseInput(input);
            }
        } catch (UwunzheException e) {
            UwunzheException.printException(e);
        }

        // Close the scanner at the end of the program
        UserInput.closeScanner();
    }

    public static void main(String[] args) {
        Printer.printInitMsg();
        loop();
        Printer.printExitMsg();
    }
}
