package main;

import tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

import static main.Command.*;
import static storage.Storage.loadData;

public class Duke {
    public static void userInput() throws DukeException {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        ArrayList<String> stringList = loadData();
        ArrayList<Task> taskList = changePresentationFormat(stringList);

        // Start of user input
        while (true) {
            userInput = scanner.nextLine().toLowerCase(); // Takes in user input
            String[] splitInput = userInput.split("\\s+"); // split by whitespaces
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

                    userMarkOrUnmark(command, userInput, taskList, stringList);
                    continue;

                case "todo":
                    if (addTask(taskList, userInput, splitInput, Commands.Todo)) {
                        saveDataIntoBothArrays (taskList, stringList, userInput);
                    }
                    continue;

                case "deadline":
                    if (addTask(taskList, userInput, splitInput, Commands.Deadline)) {
                        saveDataIntoBothArrays (taskList, stringList, userInput);
                    }
                    continue;

                case "event":

                    if (addTask(taskList, userInput, splitInput, Commands.Event)) {
                        saveDataIntoBothArrays (taskList, stringList, userInput);
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