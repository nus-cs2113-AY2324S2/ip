package main;

import java.util.ArrayList;
import java.util.Scanner;


import tasks.TaskList;


import static main.Command.*;
import static storage.Storage.loadData;
import static storage.Storage.changePresentationFormat;

public class Parser {


    /**
     * Run the specific function based on the type of user input supplied.
     */

    public static void userInput() throws DukeException {
        Scanner scanner = new Scanner(System.in);
        String originalUserInput;
        ArrayList<String> stringList = loadData();
        ArrayList<TaskList> taskList = changePresentationFormat(stringList);

        // Start of user input
        while (true) {
            originalUserInput = scanner.nextLine();
            String[] splitInput = originalUserInput.split("\\s+"); // split by whitespaces
            String command = splitInput[0]; // main command of user

            switch (command) {
                case "bye":
                    System.out.println("Bye human. Come back soon !");
                    break;

                case "list":
                    if (splitInput.length != 1) {
                        System.out.println("This command requires no additional argument. Please try again!");
                        continue;
                    }
                    userList(taskList);
                    continue;

                case "mark":
                case "unmark":
                    userMarkOrUnmark(command, originalUserInput, taskList, stringList);
                    continue;

                case "todo":
                    if (addTask(taskList, originalUserInput, splitInput, Commands.Todo)) {
                        saveDataIntoListString (taskList, stringList, originalUserInput);
                    }
                    continue;

                case "deadline":
                    if (addTask(taskList, originalUserInput, splitInput, Commands.Deadline)) {
                        saveDataIntoListString (taskList, stringList, originalUserInput);
                    }
                    continue;

                case "event":
                    if (addTask(taskList, originalUserInput, splitInput, Commands.Event)) {
                        saveDataIntoListString (taskList, stringList, originalUserInput);
                    }
                    continue;

                case "delete":
                    if (removeElementFromBothArrays(taskList, stringList, splitInput)) {
                        continue;
                    }

                case "find":
                    if (splitInput.length != 2) {
                        System.out.println("This command requires exactly 1 argument. Please try again!");
                        continue;
                    }
                    findMatchingTasks(splitInput[1], taskList, stringList);
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
