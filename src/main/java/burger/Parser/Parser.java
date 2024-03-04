package burger.Parser;

import burger.TaskList.TaskList;
import burger.UI.BurgerException;
import burger.UI.Utilities;

import static burger.Storage.BurgerFileClass.getSaveFile;
import static burger.UI.Utilities.printLine;
import static burger.UI.Utilities.printUnknownInputError;
import static burger.UI.Utilities.printEmptyDescription;
import static burger.UI.Utilities.printErrorMessage;

public class Parser {

    public static final int COMMAND_IDX = 0;

    /**
     * Starts the programme. Retrieves any save file and starts polling for user input.
     * Returns an error message if the bot cannot recognise the input.
     * Breaks the loop when user exits the program.
     *
     * @param myList the list created to store the tasks.
     * @param ui the ui created to handle user interfaces.
     */
    public static void start(TaskList myList, Utilities ui) {
        getSaveFile(myList);
        boolean isPolling = true;
        while (isPolling) {
            String text = ui.getUserInput();
            String[] textArray = text.split(" ");
            if (!isValidCommand(textArray, myList)) {
                switch (text.trim().toLowerCase()) {
                case "bye":
                    isPolling = false;
                    break;
                case "list":
                    printLine();
                    myList.printTaskList();
                    break;
                default:
                    printUnknownInputError();
                    break;
                }
            }
        }
    }

    /**
     * Returns boolean value base on whether the input matches one of the commands.
     *
     *
     * @param textArray user input in array type.
     * @return true if it's a valid command and false otherwise.
     * @throws ArrayIndexOutOfBoundsException when the description after the command is empty.
     */
    public static boolean isValidCommand(String[] textArray, TaskList list) {
        boolean isValid = true;
        int idx;
        try {
            String command = textArray[COMMAND_IDX];
            switch (command) {
            case "mark":
                // fallthrough
            case "unmark":
                idx = list.getIdx(textArray);
                list.markTask(idx, command);
                break;
            case "todo":
                // fallthrough
            case "deadline":
                // fallthrough
            case "event":
                list.handleAddTask(textArray, command);
                break;
            case "delete":
                idx = list.getIdx(textArray);
                list.deleteTask(idx);
                break;
            case "find":
                list.findKeyword(textArray);
                break;
            default:
                isValid = false;
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            printEmptyDescription();
        } catch (BurgerException e) {
            printErrorMessage(e);
        }
        return isValid;
    }
}
