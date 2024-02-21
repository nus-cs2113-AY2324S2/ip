package main;

import tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

import static main.Command.*;
import static storage.Storage.loadData;

public class Duke {
    public static void userInput() throws DukeException {
        Scanner scanner = new Scanner(System.in);
        String originalUserInput, modifiedUserInput;
        ArrayList<String> stringList = loadData();
        ArrayList<Task> taskList = changePresentationFormat(stringList);

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
                        System.out.println("No suitable command found. Please try again!");
                        break;
                    }
                    userList(taskList);
                    continue;

                case "mark":
                case "unmark":
                    userMarkOrUnmark(command, originalUserInput, taskList, stringList);
                    continue;

                case "todo":
                    if (addTask(taskList, originalUserInput, splitInput, Commands.Todo)) {
                        saveDataIntoBothArrays (taskList, stringList, originalUserInput);
                    }
                    continue;

                case "deadline":
                    if (addTask(taskList, originalUserInput, splitInput, Commands.Deadline)) {
                        saveDataIntoBothArrays (taskList, stringList, originalUserInput);
                    }
                    continue;

                case "event":
                    if (addTask(taskList, originalUserInput, splitInput, Commands.Event)) {
                        saveDataIntoBothArrays (taskList, stringList, originalUserInput);
                    }
                    continue;

                case "delete":
                    if (removeElementFromBothArrays(taskList, stringList, splitInput)) {
                        continue;
                    }

                default:
                    System.out.println("No suitable command found. Please try again!");
                    continue;
            }
            scanner.close();
            return;
        }
    }

    public static void main(String[] args) throws DukeException {
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Bob\n" +
                "What can I do for you?\n" +
                "____________________________________________________________");
        userInput();
    }
}