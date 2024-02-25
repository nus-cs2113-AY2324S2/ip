package bob;

import bob.command.Command;
import bob.exceptions.InvalidArgumentException;
import bob.exceptions.InvalidCommandException;
import bob.exceptions.InvalidDateTimeException;
import bob.exceptions.InvalidTaskNumberException;
import bob.utils.Parser;
import bob.utils.TaskManager;
import bob.utils.StateManager;
import bob.utils.Ui;

import java.io.IOException;

/**
 * Entry point for the Bob Chat-Bot. The Chat-Bot will interact with the User and obtain their input.
 */
public class Bob {
    /**
     * Runs the program until termination by the User.
     *
     * @throws IOException If the program has no read/write access to ./data/state.txt.
     */
    public void run() throws IOException {
        Ui userInterface = new Ui();
        userInterface.printLogo();
        userInterface.printWelcome();

        TaskManager manager = StateManager.loadState();
        Parser inputParser = new Parser();

        while (userInterface.hasMoreInput()) {
            try {
                String userCommand = userInterface.readCommand();
                Command currentCommand = inputParser.processUserCommand(userCommand, manager, userInterface);

                if (currentCommand.isExitCommand()) {
                    StateManager.saveState(manager);
                    break;
                }

                String executionResult = currentCommand.executeCommand();
                userInterface.print(executionResult);
            } catch (IOException | InvalidCommandException | InvalidTaskNumberException |
                     InvalidArgumentException | InvalidDateTimeException exception) {
                // Todo: Account for IOException handling when program cannot write to state file. Consider throwing
                // a RuntimeException.
                userInterface.clearInput();
                userInterface.print(exception.getMessage());
            }
        }

        userInterface.printExit();
    }
}
