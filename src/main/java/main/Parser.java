package main;

import java.util.ArrayList;
import java.util.Scanner;

import tasks.TaskList;

import static main.Command.addDeadline;
import static main.Command.addEvent;
import static main.Command.addToDo;
import static main.Command.findMatchingTasks;
import static main.Command.markTasks;
import static main.Command.printList;
import static main.Command.removeElementFromBothArrays;
import static main.Command.saveDataIntoListString;
import static main.Command.unMarkTasks;

import static storage.Storage.loadData;
import static storage.Storage.changePresentationFormat;

public class Parser {

    /* Runs the specific function based on user command. */
    public static void userInput() throws DukeException {
        Scanner scanner = new Scanner(System.in);
        String originalUserInput;
        ArrayList<String> stringList = loadData();
        ArrayList<TaskList> taskList = changePresentationFormat(stringList);

        // Start of user input
        while (true) {
            originalUserInput = scanner.nextLine();
            String[] splitInput = originalUserInput.split("\\s+");
            String command = splitInput[0]; // Main command of user

            switch (command) {
            case "bye":
                System.out.println("Bye human. Come back soon !");
                break;

            case "list":
                if (splitInput.length != 1) {
                    System.out.println("This command requires no additional argument. Please try again!");
                    continue;
                }
                printList(taskList);
                continue;

            case "mark":
                markTasks(originalUserInput, taskList, stringList);
                continue;

            case "unmark":
                unMarkTasks(originalUserInput, taskList, stringList);
                continue;

            case "todo":
                if (addToDo(taskList, originalUserInput, splitInput)) {
                    saveDataIntoListString(taskList, stringList, originalUserInput);
                }
                continue;

            case "deadline":
                if (addDeadline(taskList, originalUserInput, splitInput)) {
                    saveDataIntoListString(taskList, stringList, originalUserInput);
                }
                continue;

            case "event":
                if (addEvent(taskList, originalUserInput, splitInput)) {
                    saveDataIntoListString(taskList, stringList, originalUserInput);
                }
                continue;

            case "delete":
                removeElementFromBothArrays(taskList, stringList, splitInput);
                continue;

            case "find":
                if (splitInput.length == 2) {
                    findMatchingTasks(splitInput[1], taskList, stringList);
                    continue;
                }
                System.out.println("This command requires exactly 1 argument. Please try again!");
                continue;

            default:
                System.out.println("No suitable command found. Please try again!");
                continue;
            }
            scanner.close();
            return;
        }
    }
}
