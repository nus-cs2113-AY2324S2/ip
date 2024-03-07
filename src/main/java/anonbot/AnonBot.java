package anonbot;

import anonbot.data.AnonBotFile;
import anonbot.data.AnonBotFileReader;
import anonbot.exception.InitialisationException;
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
        try {
            AnonBotFile.initialiseDefaultDirectory();
        } catch (InitialisationException e) {
            e.printErrorMessage();
            return;
        }

        AnonBotFileReader.loadAnonBotData();
        Ui.printGreetings();
        runMainLoop();
    }
}
