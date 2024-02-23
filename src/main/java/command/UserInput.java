package command;

import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.InputMismatchException;
import java.util.List;

public class UserInput {
    public static void parseInput(String input, Scanner in, List<Task> taskList) {

        int taskID;

        switch (input.toLowerCase()) {

        case "list":
            printLists(taskList);
            break;

        case "bye":
            break;

        case "mark":
            taskID = in.nextInt();
            executeMark(taskList, taskID);
            break;

        case "unmark":
            try {
                taskID = in.nextInt();
                executeUnmark(taskList, taskID);
            } catch (InputMismatchException e) {
                System.out.println("A number please");
            }
            break;

        case "todo":
            input = in.nextLine();
            parseToDo(input, taskList);
            break;

        case "deadline":
            input = in.nextLine();
            parseDeadline(input, taskList);
            break;

        case "event":
            input = in.nextLine();
            parseEvent(input, taskList);
            break;

        default:
            // System.out.println("Please input a valid command");
            break;
        }
    }

    private static void printLists(List<Task> taskList) {

        for (int i = 1; i <= taskList.size(); i += 1) {
            System.out.print(i + ".");
            System.out.println(taskList.get(i - 1));
        }
    }

    private static void executeMark(List<Task> taskList, int taskID) {
        try {

            taskList.get(taskID - 1).markCompleted();
            System.out.println("Marking as done:");
            System.out.println(taskList.get(taskID - 1));

        } catch (IndexOutOfBoundsException e) {

            System.out.println("Invalid Index");

        }
    }

    private static void executeUnmark(List<Task> taskList, int taskID) {
        try {

            taskList.get(taskID - 1).markUncompleted();
            System.out.println("Marking as undone:");
            System.out.println(taskList.get(taskID - 1));

        } catch (IndexOutOfBoundsException e) {

            System.out.println("Invalid index");

        }
    }

    private static void parseToDo(String input, List<Task> taskList) {

        taskList.add(new ToDo(input.trim()));
        System.out.println("Added ToDo: " + input);

    }

    private static void parseDeadline(String input, List<Task> taskList) {
        try {

            taskList.add(new Deadline(input));
            System.out.println("Added Deadline: " + taskList.get(taskList.size() - 1).getName());

        } catch (JohnException e) {

            System.out.println("Invald input");

        }
    }

    private static void parseEvent(String input, List<Task> taskList) {
        try {

            taskList.add(new Event(input));
            System.out.println("Added Event: " + taskList.get(taskList.size() - 1).getName());

        } catch (JohnException e) {

            System.out.println("Invalid input");

        }
    }

}
