package util;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.util.Scanner;

public class Parser {
    public static String getCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }

    public static String getTask(String input) {
        String[] splitInput = input.split(" ", 2);
        return splitInput.length > 1 ? splitInput[1] : "";
    }

    private static void getResponse(String input, TaskList tasks) {
        String command = getCommand(input);
        String task = getTask(input);
        try {
            switch (command) {
                case "list":
                    tasks.listTasks();
                    break;
                case "mark":
                    tasks.markTask(Integer.parseInt(task));
                    break;
                case "unmark":
                    tasks.unmarkTask(Integer.parseInt(task));
                    break;
                case "todo":
                    tasks.addTask(new Todo(task.trim()));
                    break;
                case "deadline":
                    String[] deadlineSplit = task.split("/");
                    tasks.addTask(new Deadline(deadlineSplit[0].trim(),
                            deadlineSplit[1].substring(3).trim()));
                    break;
                case "event":
                    String[] eventSplit = task.split("/");
                    tasks.addTask(new Event(eventSplit[0].trim(),
                            eventSplit[1].substring(5).trim(),
                            eventSplit[2].substring(3).trim()));
                    break;
                case "delete":
                    tasks.deleteTask(Integer.parseInt(task));
                    break;
                default:
                    System.out.println("Your command is invalid, invalid. Try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You are missing a /. Do not let this happen again.");
        }

    }

    public static void getInput(TaskList tasks) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            getResponse(line, tasks);
            line = in.nextLine();
        }
    }
}