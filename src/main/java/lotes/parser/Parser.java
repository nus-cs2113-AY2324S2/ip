package lotes.parser;

import java.util.Scanner;

import lotes.storage.Storage;
import lotes.task.TaskList;
import lotes.ui.UserInterface;
import lotes.commands.AddDeadlineCommand;
import lotes.commands.AddEventCommand;
import lotes.commands.AddToDoCommand;
import lotes.commands.ListCommand;
import lotes.commands.MarkCommand;
import lotes.commands.UnMarkCommand;
import lotes.commands.DeleteCommand;
import lotes.commands.FindCommand;
import lotes.commands.ExitCommand;

public class Parser {

    // Parses the user input to get the command to perform.

    public static String parseInputCommand(String userInput) {
        String[] userInputArray = userInput.split(" ", 2);

        String command = userInputArray[0];

        return command;
    }

    // Reads and parse the user input to perform its respective actions.

    public static boolean performUserInput(String userInput, TaskList taskList) {
        boolean isExit = false;
        String command = parseInputCommand(userInput);

        switch (command) {
        case ExitCommand.EXIT_COMMAND_WORD:
        case ExitCommand.BYE_COMMAND_WORD:
            ExitCommand exitCommand = new ExitCommand();
            exitCommand.run();
            isExit = true;
            break;

        case ListCommand.COMMAND_WORD:
            ListCommand listCommand = new ListCommand();
            listCommand.execute();
            break;

        case MarkCommand.COMMAND_WORD:
            MarkCommand markCommand = new MarkCommand();
            markCommand.run(userInput);
            break;

        case UnMarkCommand.COMMAND_WORD:
            UnMarkCommand unMarkCommand = new UnMarkCommand();
            unMarkCommand.run(userInput);
            break;

        case AddToDoCommand.COMMAND_WORD:
            AddToDoCommand addToDoCommand = new AddToDoCommand();
            addToDoCommand.run(userInput);
            break;

        case AddDeadlineCommand.COMMAND_WORD:
            AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand();
            addDeadlineCommand.run(userInput);
            break;

        case AddEventCommand.COMMAND_WORD:
            AddEventCommand addEventCommand = new AddEventCommand();
            addEventCommand.run(userInput);
            break;

        case DeleteCommand.COMMAND_WORD:
            DeleteCommand deleteCommand = new DeleteCommand();
            deleteCommand.run(userInput);
            break;

        case FindCommand.COMMAND_WORD:
            taskList.findTask(userInput);
            break;

        default:
            System.out.println("Please enter a command I can understand :(");
        }

        if (!command.equals("list")) {
            Storage.updateFile();
        }

        return isExit;
    }

    // Handle errors by catching exceptions.

    public static boolean catchExceptionOnExecution(String userInput, TaskList taskList) {
        boolean isExit = false;
        try {
            if (performUserInput(userInput, taskList)) { // if ixExit returns true
                isExit = true;
                return isExit;
            }

        } catch (NumberFormatException e) {
            UserInterface.showNumberFormatException();

        } catch (NullPointerException e) {
            UserInterface.showNullPointerException();

        } catch (IndexOutOfBoundsException e) {
            UserInterface.showIndexOutOfBoundsException();

        }
        return isExit;
    }

    //  Continuously Interpreting the user input

    public static void interpretUserInput(Scanner inputCommand, TaskList taskList) {

        while (inputCommand.hasNextLine()) { // Prompt for continuous user input
            String userInput = inputCommand.nextLine();

            if (catchExceptionOnExecution(userInput, taskList)) {
                break; // Exit reading and interpreting next line
            }
        }
    }
}
