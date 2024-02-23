package Chelle;

import java.util.ArrayList;
import java.util.Scanner;
import ChelleCommands.*;
import ChelleExceptions.*;

public class Chelle {
    private static final String FILE_PATH = "./ChelleTasks.txt";


    public static void main(String[] args) {
        // Load tasks from the hard disk when the chatbot starts up
        ArrayList<Task> tasks = SaveTasks.loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Chelle.\nI like to talkity talkity talk!");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            CommandType userCommand;
            try {
                userCommand = CommandType.valueOf((userInput.split(" ")[0]).toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Chelle: Invalid command. Please start your command with one of the following commands: \n" +
                        "'list', 'mark', 'unmark', 'todo', 'deadline', 'event', or 'delete'.");
                continue;
            }

            switch (userCommand) {
            case BYE:
                SaveTasks.saveTasksToFile(tasks);
                System.out.println("Chelle: Bye! Hope to see you again soon!");
                scanner.close();
                return;

            case LIST:
                System.out.println("Chelle: ");
                displayTasks(tasks);
                break;

            case MARK:
            case UNMARK:
            case TODO:
            case DEADLINE:
            case EVENT:
            case DELETE:
                try {
                    handleCommand(userInput, tasks, userCommand);
                } catch (InvalidCommandFormatException e) {
                    break;
                }
                break;

            default:
                System.out.println("Chelle: Invalid command. Please start your command with one of the following commands: \n" +
                        "'list', 'mark', 'unmark', 'todo', 'deadline', 'event', or 'delete'.");
                break;
            }
        }
    }

    private static void displayTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    private static void handleCommand(String userInput, ArrayList<Task> tasks, CommandType command) throws InvalidCommandFormatException {
        switch (command) {
        case MARK:
            if (userInput.length() < 6) {
                throw new InvalidCommandFormatException(command);
            } else {
                userInput = userInput.substring(5);
            }

            try {
                int taskIndex = Integer.parseInt(userInput) - 1;
                if (isValidTaskIndex(taskIndex, tasks)) {
                    tasks.get(taskIndex).markDone();
                    System.out.println("Chelle: Nice! I've marked this task as done:\n        " +
                            tasks.get(taskIndex).toString());
                } else {
                    System.out.println("Chelle: Invalid task index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Chelle: Invalid task index.");
            }
            break;

        case UNMARK:
            if (userInput.length() < 8) {
                throw new InvalidCommandFormatException(command);
            } else {
                userInput = userInput.substring(7);
            }

            try {
                int taskIndex = Integer.parseInt(userInput) - 1;
                if (isValidTaskIndex(taskIndex, tasks)) {
                    tasks.get(taskIndex).markUndone();
                    System.out.println("Chelle: OK, I've marked this task as not done yet:\n        " +
                            tasks.get(taskIndex).toString());
                } else {
                    System.out.println("Chelle: Invalid task index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Chelle: Invalid task index.");
            }
            break;

        case TODO:
            if (userInput.length() < 6) {
                throw new InvalidCommandFormatException(command);
            } else {
                tasks.add(new ToDo(userInput.substring(5)));
                Task.addMessage(tasks);
            }
            break;

        case DEADLINE:
            if (userInput.length() < 10 || !userInput.contains("/by")) {
                throw new InvalidCommandFormatException(command);
            } else {
                tasks.add(new Deadline(userInput.substring(9)));
                Task.addMessage(tasks);
            }
            break;

        case EVENT:
            if (userInput.length() < 7 || !(userInput.indexOf("/from") < userInput.indexOf("/to")) || !(userInput.contains("/from"))){
                throw new InvalidCommandFormatException(command);
            } else {
                tasks.add(new Event(userInput.substring(6)));
                Task.addMessage(tasks);
            }
            break;

        case DELETE:
            if (userInput.length() < 8) {
                throw new InvalidCommandFormatException(CommandType.DELETE);
            } else {
                userInput = userInput.substring(7);
            }

            try {
                int taskIndex = Integer.parseInt(userInput) - 1;
                if (isValidTaskIndex(taskIndex, tasks)) {
                    Task.delMessage(tasks, taskIndex);
                    tasks.remove(taskIndex);
                } else {
                    System.out.println("Chelle: Invalid task index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Chelle: Invalid task index.");
            }
            break;

        default:
            System.out.println("Chelle: Error");
            break;
        }
    }

    private static boolean isValidTaskIndex(int index, ArrayList<Task> tasks) {
        return index >= 0 && index < tasks.size();
    }
}
