package anonbot;

import exception.InvalidCommandException;
import misc.CommandManager;
import misc.Status;

public class AnonBot {
    private static void runMainLoop() {
        Status commandStatus = Status.STATUS_OK;
        while (commandStatus != Status.STATUS_EXIT) {
            String userInput = Ui.getUserInput();
            try{
                commandStatus = CommandManager.processCommand(userInput);
            } catch (InvalidCommandException e) {
                e.printErrorMessage();
            }
        }
    }

    public static void main(String[] args) {
        Ui.printGreetings();
        runMainLoop();
    }
}
