import java.util.Scanner;

public class Sunny {
    private static Task[] tasks = new Task[100];
    private static int counter = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm Sunny");
        System.out.println("What can I do for you?");
        System.out.println(" ");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    printTaskList();
                } else if (command.startsWith("mark")) {
                    markTaskAsDone(command);
                } else if (command.startsWith("unmark")) {
                    unmarkTaskAsDone(command);
                } else if (command.startsWith("todo")) {
                    handleTodoCommand(command);
                } else if (command.startsWith("deadline")) {
                    handleDeadlineCommand(command);
                } else if (command.startsWith("event")) {
                    handleEventCommand(command);
                } else {
                    handleUnknownCommand(command);
                }
            } catch (Exception e) {
                handleErrors(e);
            }
        }
    }

    private static void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
        System.out.println(" ");
    }

    private static void markTaskAsDone(String command) {
        int taskIndex = extractTaskIndex(command);
        if (isValidTaskIndex(taskIndex)) {
            tasks[taskIndex - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[taskIndex - 1]);
        } else {
            printInvalidTaskIndexMessage();
        }
        System.out.println(" ");
    }

    private static void unmarkTaskAsDone(String command) {
        int taskIndex = extractTaskIndex(command);
        if (isValidTaskIndex(taskIndex)) {
            tasks[taskIndex - 1].unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[taskIndex - 1]);
        } else {
            printInvalidTaskIndexMessage();
        }
        System.out.println(" ");
    }

    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex > 0 && taskIndex <= counter;
    }

    private static void handleTodoCommand(String command) {
        try {
            if (command.length() <= 5) {
                throw new StringIndexOutOfBoundsException();
            }

            tasks[counter] = new Todo(command.substring(5));
            System.out.println(tasks[counter]);
            counter++;
            printTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            handleErrors(e);
        }
    }

    private static void handleDeadlineCommand(String command) {
        try {
            int dividerPosition = command.indexOf("/by ");
            if (dividerPosition == -1) {
                throw new StringIndexOutOfBoundsException();
            }

            tasks[counter] = new Deadline(command.substring(0, dividerPosition - 1), command.substring(dividerPosition + 4));
            System.out.println(tasks[counter]);
            counter++;
            printTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            handleErrors(e);
        }
    }

    private static void handleEventCommand(String command) {
        try {
            int from = command.indexOf("/from ");
            int to = command.indexOf("/to ");
            if (from == -1 || to == -1) {
                throw new StringIndexOutOfBoundsException();
            }

            tasks[counter] = new Event(command.substring(0, from - 1), command.substring(from + 6, to - 1), command.substring(to + 4));
            System.out.println(tasks[counter]);
            counter++;
            printTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            handleErrors(e);
        }
    }

    private static void handleUnknownCommand(String command) {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(" ");
    }



    private static void printInvalidTaskIndexMessage() {
        System.out.println("Invalid task index. Please provide a valid task index.");
    }

    private static void printTaskCountMessage() {
        String taskNoun = (counter == 1) ? "task" : "tasks";
        System.out.println("Now you have " + counter + " " + taskNoun + " in the list." + System.lineSeparator());
    }

    private static int extractTaskIndex(String command) {
        String[] parts = command.split(" ");
        return (parts.length >= 2) ? Integer.parseInt(parts[1]) : -1;
    }

    private static void handleErrors(Exception e) {
        if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.println("OOPS!!! The command seems to be incomplete or incorrect.");
        } else if (e instanceof NumberFormatException) {
            System.out.println("OOPS!!! Please provide a valid task index.");
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println("OOPS!!! The description cannot be empty.");
        } else {
            System.out.println("OOPS!!! I'm sorry, but I encountered an unexpected error.");
            e.printStackTrace(); // Print stack trace for debugging purposes
        }
        System.out.println(" ");
    }
}