import java.util.List;
import java.util.Scanner;


public class Ui {
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    public static final int SPLIT_INTO_TWO_PARTS = 2;
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String BYE = "bye";
    public static final String DELETE = "delete";
    public static final String GOODBYE = "Goodbye... It may be a mere few seconds for you but an eternity for me.";


    public static void printStartingMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Sup! I'm DavinciBot! I was the smartest man alive, but now I am just a list maker.");
        System.out.println("Enter commands, and I will echo them back to you, as well as add them to your list.");
        System.out.println("If there were things in your list that you previously had," +
                " I will show what you have told me previously.");
        System.out.println("Type 'bye' to end the conversation.");
        System.out.println("Type 'list' to see your to-do list.");
        System.out.println("Type 'mark' to mark a task as done.");
        System.out.println("Type 'unmark' to mark a task as not done.");
        System.out.println("Type 'todo <work>' to add a task to the list.");
        System.out.println("Type 'deadline <description> /by <deadline>' to add a task with a deadline to the list.");
        System.out.println("Type 'event <description> /from <start> /to <end>' to add an event to the list.");
        System.out.println("Type 'delete <index>' to delete a task from your list.");
        System.out.println("Type 'find <keyword>' to list all the tasks that have those keywords.");
        System.out.println("See ya bucko!");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Handles user commands and performs corresponding actions based on the input.
     */
    public static void userCommand() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = Ui.getUserInput();
            if (userInput.equalsIgnoreCase(BYE)) {
                Ui.printMessage(GOODBYE);
                break;
            }
            if (userInput.toLowerCase().startsWith(TODO) ||
                    userInput.toLowerCase().startsWith(DEADLINE) ||
                    userInput.toLowerCase().startsWith(EVENT)) {
                TaskList.handleUserInput(userInput);
            } else if (userInput.toLowerCase().startsWith(DELETE)) {
                try {
                    String[] parts = userInput.split(" ", SPLIT_INTO_TWO_PARTS);
                    TaskList.ableToDelete(parts);
                } catch (DavinciException e) {
                    Ui.printMessage("Error: " + e.getMessage());
                } catch (NumberFormatException e) {
                    Ui.printMessage("Error: Invalid task index format.");
                }
            } else {
                Parser.parseUserInput(userInput, TaskList.getTaskList());
            }
        }
        scanner.close();
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What do you want me to do? ");
        return scanner.nextLine().trim();
    }

    public static void displayTaskList(List<Task> taskList) {
        System.out.println(LINE_SEPARATOR);
        if (taskList.isEmpty()) {
            System.out.println("No tasks entered yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            checkForTask(taskList);
        }
        System.out.println(LINE_SEPARATOR);
    }

    public static void displayFindTask(List<Task> taskList) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("These are the tasks that matches your search: ");
        checkForTask(taskList);
        System.out.println(LINE_SEPARATOR);
    }

    public static void displaySingleTask(Task task) {
        String taskType;
        if (task instanceof Deadline) {
            taskType = "[D]";
        } else if (task instanceof Todo) {
            taskType = "[T]";
        } else {
            taskType = "[E]";
        }
        System.out.println(taskType +
                " [" + task.getStatusIcon() + "] " +
                task.getDescription());
    }

    private static void checkForTask(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            String taskType;
            Task task = taskList.get(i);
            if (task instanceof Deadline) {
                taskType = "[D]";
            } else if (task instanceof Todo) {
                taskType = "[T]";
            } else {
                taskType = "[E]";
            }
            System.out.println((i + 1) + ". " + taskType +
                    " [" + task.getStatusIcon() + "] " +
                    task.getDescription());
        }
    }


    public static void printMessage(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(LINE_SEPARATOR);
    }

    public static void echoTask(List<Task> taskList) {
        System.out.println(LINE_SEPARATOR);
        Task addedTask = taskList.get(taskList.size() - 1);
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }
}
