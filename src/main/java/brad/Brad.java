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
import brad.tasks.TaskType;
import brad.ui.Ui;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.EmptyStackException;
import java.util.Scanner;


public class Brad {
    private static TaskList tasklist = new TaskList();
    private static Storage storage = new Storage();
    private static Ui ui = new Ui();
    private static boolean toSave = true;

    public static void main(String[] args) {
        ui.greetUser();
        run(canInitialize());
    }

    public static boolean canInitialize() {
        boolean canStart = true;
        try {
            storage.initializeFile(tasklist);
        } catch (FileNotFoundException e) {
            ui.printFileNotFound();
            toSave = false;
        } catch (dataCorruptedException e) {
            ui.printDataCorrupted();
            canStart = false;
        }
        return canStart;
    }

    private static void run(boolean canInitialize) {
        if (canInitialize) {
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
        while (canInitialize) {
            String userInput = ui.getUserInput();
            Parser parser = new Parser();
            try {
                parser.parseCommand(userInput);
                Command command = parser.getCommand();
                if (command == Command.BYE) {
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

