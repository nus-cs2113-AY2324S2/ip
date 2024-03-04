package burger.Parser;

import java.util.Scanner;

import burger.TaskList.TaskList;

import static burger.Storage.BurgerFileClass.getSaveFile;
import static burger.Storage.BurgerFileClass.setSaveFile;
import static burger.UI.Utilities.*;

public class Parser {

    public static final int COMMANDIDX = 0;

    public static void parseUserInput() {
        TaskList myList = getSaveFile();
        Scanner input = new Scanner(System.in);
        boolean isPolling = true;
        while (isPolling) { // move to parser
            String text = input.nextLine();
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
        setSaveFile(myList);
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
            String command = textArray[COMMANDIDX];
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
        }
        return isValid;
    }
}
