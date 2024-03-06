package ui;

import java.util.Scanner;

public class Ui {

    private final String name;
    private final Scanner scanner = new Scanner(System.in);

    public Ui(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        String command = scanner.nextLine().trim();
        // scanner.close();
        return command;
    }

    /*
    public void echoCommands() {
        String command = readCommand();

        do {
            command = readCommand();
            if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("mark ")) {
                markTaskAsDone(command.substring(5));
            } else if (command.startsWith("unmark ")) {
                unmarkTaskAsDone(command.substring(7));
            } else if (command.startsWith("delete")) {
                int index = Integer.parseInt(command.substring(7));
                deleteTask(index);
            } else if (!command.startsWith("bye")) {
                try {
                    add(command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!command.equals("bye"));
    }
    */

    public void showLine() {
        System.out.println("=========================");
    }

    public void showSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    // Error messages:

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showTaskAdded(String description) {
        System.out.println("Added: " + description);
    }

    public void showTaskNotFound() {
        System.out.println("Task not found.");
    }

    // invalid input

    public void invalidCommand() {
        System.out.println("Invalid command! Please try again.");
    }

    public void invalidTaskFormat(String stage) {
        switch (stage) {
            case "task":
                System.out.println("Invalid task format.");
                break;
            case "todo":
                System.out.println("Invalid TODO format. Please add description for todo");
                break;
            case "event":
                System.out.println("Invalid EVENT format. Please include the start and end times.");
                break;
            case "deadline":
                System.out.println("Invalid DEADLINE format. Please include the due time.");
                break;
            default:
                break;
        }
    }

    public void invalidIndex() {
        System.out.println("Invalid index!");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("=========================");
    }
    // Add other methods for displaying messages to the user
}