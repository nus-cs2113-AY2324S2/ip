package anonbot;

import anonbot.exception.InvalidCommandException;
import anonbot.misc.CommandManager;
import anonbot.misc.Status;

public class AnonBot {
    private static void runMainLoop() {
        Status commandStatus = Status.STATUS_OK;
        while (commandStatus != Status.STATUS_EXIT) {
            String userInput = Ui.getUserInput();
            Ui.printSectionBar(false);
            try {
                commandStatus = CommandManager.processCommand(userInput);
            } catch (InvalidCommandException e) {
                e.printErrorMessage();
            } finally {
                Ui.printSectionBar(true);
            }
        }
    }

    public static void main(String[] args) {
        Ui.printGreetings();
        runMainLoop();
    }
}
