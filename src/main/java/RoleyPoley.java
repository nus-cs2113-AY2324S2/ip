import roleypoley.command.HandleCommand;
import roleypoley.data.ReadFile;
import roleypoley.exception.RoleyPoleyFileException;
import roleypoley.ui.TextUi;


/**
 * Entry point of RoleyPoley ChatBot
 * Initializes the application and starts interaction with the user
 */
public class RoleyPoley {
    public static void main(String[] args) throws RoleyPoleyFileException {
        ReadFile.readFileToArrayList();
        run();
    }

    /**
     * runs program till termination
     */
    public static void run() {
        TextUi.welComeMessage();
        runCommandLoopUntilExitCommand();
    }
 
    /** Reads the user command and executes it, until the user issues the exit command.  */
    private static void runCommandLoopUntilExitCommand() {
        boolean isExit;
        do {
            String command = TextUi.getUserInput();
            isExit = HandleCommand.executeCommand(command);
        } while (!isExit);
    }
}




