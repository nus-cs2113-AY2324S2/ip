package lotes.parser;

import java.util.Objects;
import java.util.Scanner;

import lotes.Storage;
import lotes.task.TaskList;

public class Parser {

    // Parses the user input to get the command to perform.

    public static String[] parseInputCommand(String userInput) {
        String[] words = userInput.split(" ", 2);

        return words;
    }

    // Reads and parse the user input to perform its respective actions.

    public static boolean performUserInput(String userInput, TaskList taskList) throws LotesException {
        boolean isExit = false;
        String[] command = parseInputCommand(userInput);

        switch (command[0]) {
        case "bye":
            System.out.println(TaskList.goodbyeMessage);
            isExit = true;
            break;
        case "list":
            taskList.printTasksList();
            break;
        case "mark":
            taskList.markTask(userInput);
            break;
        case "unmark":
            taskList.unMarkTask(userInput);
            break;
        case "todo":
            taskList.addToDo(userInput);
            break;
        case "deadline":
            taskList.addDeadline(userInput);
            break;
        case "event":
            taskList.addEvent(userInput);
            break;
        case "delete":
            taskList.deleteTask(userInput);
            break;
        case "add":
            taskList.addNewTask(userInput);
            break;
        default:
            if(command.length < 2) {
                throw new LotesException();
            }
        }

        if (command[0].equals("todo") || command[0].equals("deadline") || command[0].equals("event")
        || command[0].equals("add") || command[0].equals("mark") || command[0].equals("unmark")) {
            Storage.updateFile(taskList);
        }

        return isExit;
    }

    // Handle errors by catching exceptions.

    public static boolean isException(String userInput, TaskList taskList) {
        try {
            if (Parser.performUserInput(userInput, taskList)) { // if ixExit returns true
                return true;
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter enter a number without other characters.");

        } catch (NullPointerException e) {
            System.out.println("Please enter enter a number within the list.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter at least 2 arguments and within bounds.");

        } catch (LotesException e) {
            System.out.println("Please enter a command I can understand :(");

        }
        return false;
    }

    //  Continuously Interpreting the user input

    public static void interpretUserInput(Scanner inputCommand, TaskList taskList) {

        while (inputCommand.hasNextLine()) { // Prompt for continuous user input
            String userInput = inputCommand.nextLine();

            if (isException(userInput, taskList)) {
                break; // Exit reading and interpreting next line
            }
        }
    }



}
