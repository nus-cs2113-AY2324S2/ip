package Parser;

import NewExceptions.EmptyArgumentException;
import NewExceptions.EmptyStatementException;
import Tasking.TaskList;
import Tasking.Todo;
import Tasking.Davvy;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Parser {
    public static String[] processInput (String inputText) throws EmptyStatementException, IllegalArgumentException {
        // String processing, \\b is used to signal the end of a word
        String[] processedInput = inputText.split("\\b", 2);
        if (inputText.isEmpty()) {
            throw new EmptyStatementException();
        }
        // String Cleaning
        processedInput[0] = processedInput[0].trim().toLowerCase();
        processedInput[1] = processedInput[1].isEmpty() ? "" : processedInput[1].trim();
        return processedInput;
    }

    public static boolean processCommand (String[] input) throws EmptyArgumentException, IOException {
        String commandType = input[0];
        String commandArg = input[1];
        int taskIndex;

        switch (commandType) {
        case "list":
            TaskList.printList();
            break;
        case "mark":
            taskIndex = parseInt(commandArg.trim()) - 1;
            TaskList.getTask(taskIndex).markDone(true);
            break;
        case "unmark":
            taskIndex = parseInt(commandArg.trim()) - 1;
            TaskList.getTask(taskIndex).markNotDone(true);
            break;
        case "bye":
            return true;
        case "todo":
            if (commandArg.isEmpty()) {
                throw new EmptyArgumentException();
            }
            Todo inputTodo = new Todo(commandArg);
            TaskList.addTask(inputTodo, false);
            break;
        case "deadline":
            if (commandArg.contains("/by")) {
                Davvy.handleDeadline(commandArg);
            } else {
                System.out.println("Please put a date!");
            }
            break;
        case "event":
            if (commandArg.contains("/from") && commandArg.contains("/to")) {
                Davvy.handleEvent(commandArg);
            } else {
                System.out.println("Please put a correct time!");
            }
            break;
        case "delete":
            // This regex refers to all numbers
            if (commandArg.matches("[0-9]+")) {
                TaskList.deleteTask(Integer.parseInt(commandArg));
            } else {
                System.out.println("Please enter a number!");
            }
            break;
        default:
            System.out.println("Unknown Command, type something I know please!");
            break;
        }
        return false;
    }
}
