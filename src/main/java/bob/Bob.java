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

public class Bob {
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
                userInterface.clearInput();
                userInterface.print(exception.getMessage());
            }
        }

        userInterface.printExit();
    }
}
