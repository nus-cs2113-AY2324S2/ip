package brad;

import brad.exceptions.invalidCommandException;
import brad.parser.Command;
import brad.parser.Parser;
import brad.ui.Ui;
import brad.exceptions.emptyArgumentException;
import brad.exceptions.invalidNumberException;
import brad.exceptions.dataCorruptedException;
import brad.storage.Storage;
import brad.tasks.TaskList;
import brad.ui.Ui;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Brad {
    private static TaskList tasklist = new TaskList();
    private static Storage storage = new Storage();
    private static Ui ui = new Ui();
    private static boolean toSave = true;

    /**
     * Main loop of the program.
     * Greets the user and checks if file exists. If everything is in order,
     * enable task saving function. It is disabled if file doesn't exist.
     * The Brad chatbot will then run continuously to query for user input if data
     * is not corrupted. Prompts user to check the file if it is corrupted.
     * Program exits immediately if user types "bye".
     */
    public static void main(String[] args) {
        ui.greetUser();
        boolean canInitialize = canInitialize();
        run(canInitialize);
    }

    /**
     * Checks if file can be found locally and if it can be loaded successfully.
     * If file exists, print out the existing content (if any) and enable saving function
     *
     * @return if program can be initialized successfully to take in user input
     */
    public static boolean canInitialize() {
        boolean canStart = true;
        try {
            storage.initializeFile(tasklist);
        } catch (FileNotFoundException e) {
            ui.printFileNotFound();
            toSave = false;
        } catch (dataCorruptedException e) {
            e.printStackTrace();
            ui.printDataCorrupted();
            canStart = false;
        }
        if (canStart) {
            if (tasklist.listSize() != 0) {
                ui.printAllTasks(tasklist);
            } else if (toSave) {
                try {
                    storage.addHeader();
                } catch (IOException e) {
                    ui.printError("Something went wrong\n" +
                            "Error message: " + e.getMessage());
                }
            }
        }
        return canStart;
    }

    /**
     * The main program that parses, executes commands and updates the file (if it exists)
     * @param canInitialize boolean if program can start taking in user input
     */
    private static void run(boolean canInitialize) {
        while (canInitialize) {
            String userInput = ui.getUserInput();
            Parser parser = new Parser();
            try {
                parser.parseCommand(userInput);
                Command command = parser.getCommand();
                if (command == Command.BYE) {
                    ui.bidFarewell();
                    break;
                }
                if (command == Command.LIST) {
                    ui.printAllTasks(tasklist);
                } else {
                    String item = tasklist.executeCommand(parser.getCommand(), parser.getUserInput());
                    ui.showResult(item, parser.getCommand(), tasklist.listSize());
                }
                if (toSave) {
                    storage.updateFile(tasklist);
                }
            } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                ui.printMissingArgument(parser.getCommand());
            } catch (invalidCommandException e) {
                ui.printUnknownCommand();
            } catch (invalidNumberException e) {
                ui.printInvalidNumber(tasklist.listSize());
            } catch (IOException e) {
                ui.printError("Something went wrong, error data: "
                        + e.getMessage());
            }
        }
    }
}

