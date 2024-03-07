package roleypoley.command;

import roleypoley.ui.TextUi;
import roleypoley.data.WriteFile;
import roleypoley.exception.RoleyPoleyParseException;
import roleypoley.parser.Parser;
import roleypoley.task.Task;


import java.io.IOException;
import java.util.ArrayList;

import static roleypoley.ui.TextUi.*;

/**
 *  Handles the various commands provided by the user
 */
public class HandleCommand {

    protected static ArrayList<Task> taskList = new ArrayList<>();
    private static String myPath = "./src/main/java/RoleyPoleyData.txt";

    public static ArrayList<Task> getTaskList() { 
        return taskList;
    }

    /**
     * Determines the command and execute it
     *
     * @param userInput full user input string
     * @return true if userInputs "bye", otherwise return false
     */
    public static boolean executeCommand(String userInput) {
        String[] splitString = userInput.split(" ");
        try {
            switch (splitString[0]) {
            case "bye":
                ExitCommand();
                createLine();
                return true;
            case "list":
                displayList(taskList, false);
                createLine();
                return false;
            case "todo", "deadline", "event":
                taskList.add(Parser.parseAddCommand(userInput));
                printAddReply(taskList);
                createLine();
                return false;
            case "delete", "unmark", "mark":
                int size_taskList = taskList.size();
                int taskNum = Parser.parseEditCommand(size_taskList, userInput);
                EditCommand(splitString[0], taskNum);
                createLine();
                return false;
            case "find":
                findCommand(userInput);
                return false;
            default:
                throw new RoleyPoleyParseException("defaultError");
            }
        } catch (RoleyPoleyParseException e) {
            createLine();
            return false;
        }
    }

    /**
     * Find tasks that contains the specified keyword
     *
     * @param userInput full user input string
     * @throws RoleyPoleyParseException if user inputs an empty keyword
     */
    private static void findCommand(String userInput) throws RoleyPoleyParseException {
        ArrayList<Task> searchList = new ArrayList<>();
        String[] splitString = userInput.split(" ");
        if (splitString.length == 2) {
            for (Task task : taskList) {
                if (task.getDescription().toLowerCase().contains(splitString[1])) {
                    searchList.add(task);
                }
            }
            displayList(searchList, true);
            createLine();
        } else {
            throw new RoleyPoleyParseException("defaultError");
        }
    }

    /**
     * Saves the data and store it in a text file
     * Print Goodbye message
     */
    private static void ExitCommand() {
        try {
            WriteFile.writeToFile(myPath, taskList);
        } catch (IOException ex) {
            System.out.println("File Error!");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Perform operations that involves editing existing task
     *
     * @param command operation to perform
     * @param taskNum task number to perform edit operation on
     */
    private static void EditCommand(String command, int taskNum) {
        switch(command) {
        case "delete":
            TextUi.printDelReply(taskList, taskNum - 1);
            taskList.remove(taskNum - 1);
            break;
        case "unmark":
            taskList.get(taskNum - 1).markAsUndone();
            break;
        case "mark":
            taskList.get(taskNum - 1).markAsDone();
            break;
        default:
            break;
        }
    }
}
