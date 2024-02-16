package Chelle;
import java.util.Scanner;
import ChelleCommands.*;
import ChelleExceptions.*;

public class Chelle {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("Hello! I'm Chelle.\nI like to talkity talkity talk!");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            CommandType userCommand;
            try {
                userCommand = CommandType.valueOf((userInput.split(" ")[0]).toUpperCase());
            } catch (IllegalArgumentException e){
                System.out.println("Chelle: Invalid command. Please start your command with one of the following commands: \n" +
                        "'list', 'mark', 'unmark', 'todo', 'deadline' or 'event'.");
                continue;
            }

            switch (userCommand) {
            case BYE:
                System.out.println("Chelle: Bye! Hope to see you again soon!");
                scanner.close();
                return;

            case LIST:
                System.out.println("Chelle: ");
                displayTasks(tasks, taskCount);
                break;

            case MARK:
            case UNMARK:
            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    taskCount = handleCommand(userInput, tasks, taskCount, userCommand);
                } catch (InvalidCommandFormatException e) {
                    break;
                }
                break;
            default:
                System.out.println("Chelle: Invalid command. Please start your command with one of the following commands: \n" +
                        "'list', 'mark', 'unmark', 'todo', 'deadline' or 'event'.");
                break;
            }
        }
    }

    private static void displayTasks(Task[] tasks, int count) {
        if (count == 0) {
            System.out.println("No tasks to display.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + tasks[i].toString());
            }
        }
    }

    private static int handleCommand(String userInput, Task[] tasks, int taskCount, CommandType command) throws InvalidCommandFormatException {
        switch (command) {

        case MARK:
            if (userInput.length() < 6) {
                throw new InvalidCommandFormatException(command);
            } else {
                userInput = userInput.substring(5);
            }

            try {
                int taskIndex = Integer.parseInt(userInput) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markDone();
                    System.out.println("Chelle: Nice! I've marked this task as done:\n        " +
                            tasks[taskIndex].toString());
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
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markUndone();
                    System.out.println("Chelle: OK, I've marked this task as not done yet:\n        " +
                            tasks[taskIndex].toString());
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
                tasks[taskCount] = new ToDo(userInput.substring(5));
                taskCount++;
                Task.message(tasks, taskCount);
            }
            break;

        case DEADLINE:
            if (userInput.length() < 10 || !userInput.contains("/by")) {
                throw new InvalidCommandFormatException(command);
            } else {
                tasks[taskCount] = new Deadline(userInput.substring(9));
                taskCount++;
                Task.message(tasks, taskCount);
            }
            break;

        case EVENT:
            if (userInput.length() < 7 || !(userInput.indexOf("/from") < userInput.indexOf("/to"))) {
                throw new InvalidCommandFormatException(command);
            } else {
                tasks[taskCount] = new Event(userInput.substring(6));
                taskCount++;
                Task.message(tasks, taskCount);
            }
            break;

        default:
            System.out.println("Chelle: Error");
            return taskCount;
        }

        return taskCount;
    }
}