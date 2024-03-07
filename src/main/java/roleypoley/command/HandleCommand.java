package roleypoley.command;

import roleypoley.ui.TextUi;
import roleypoley.data.WriteFile;
import roleypoley.exception.RoleyPoleyParseException;
import roleypoley.parser.Parser;
import roleypoley.task.Task;


import java.io.IOException;
import java.util.ArrayList;

import static roleypoley.ui.TextUi.*;


public class HandleCommand {

    protected static ArrayList<Task> taskList = new ArrayList<>();
    private static String myPath = "./src/main/java/RoleyPoleyData.txt";

    public static ArrayList<Task> getTaskList() { 
        return taskList;
    }

    public static boolean executeCommand(String userInput) {
        String[] splitString = userInput.split(" ");

        try {
            switch (splitString[0]) {
            case "bye":
                ExitCommand();
                createLine();
                return true;
            case "list":
                displayList(taskList);
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
            default:
                throw new RoleyPoleyParseException("defaultError");
            }
        } catch (RoleyPoleyParseException e) {
            createLine();
            return false;

        }

    }

    private static void ExitCommand() {
        try {
            WriteFile.writeToFile(myPath, taskList);
        } catch (IOException ex) {
            System.out.println("File Error!");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void EditCommand(String command, int taskNum) throws RoleyPoleyParseException {
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
            throw new RoleyPoleyParseException("defaultError");

        }
    }
}
