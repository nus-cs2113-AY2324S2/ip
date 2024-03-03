package Chelle;

import java.util.ArrayList;
import java.util.Scanner;
import ChelleCommands.CommandType;
import ChelleCommands.HandleCommand;
import ChelleCommands.Task;
import ChelleExceptions.InvalidCommandFormatException;

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
                // Fallthrough
            case UNMARK:
                // Fallthrough
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                // Fallthrough
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
            HandleCommand.handleMarkCommand(userInput, tasks);
            break;
        case UNMARK:
            HandleCommand.handleUnmarkCommand(userInput, tasks);
            break;
        case TODO:
            HandleCommand.handleToDoCommand(userInput, tasks);
            break;
        case DEADLINE:
            HandleCommand.handleDeadlineCommand(userInput, tasks);
            break;
        case EVENT:
            HandleCommand.handleEventCommand(userInput, tasks);
            break;
        case DELETE:
            HandleCommand.handleDeleteCommand(userInput, tasks);
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
