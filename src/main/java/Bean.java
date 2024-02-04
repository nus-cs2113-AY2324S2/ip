import java.util.Scanner;
import java.util.Arrays;

public class Bean {
    private static final String SEPARATOR = "   -------------------------------------------------";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static void printWelcomeMessage() {
        System.out.println("    Hello! I'm Bean.\n    What can I do for you?");
        System.out.println(SEPARATOR);
    }

    private static void printTaskList(TaskList tasks) {
        System.out.println("    These are the tasks in your list:");
        System.out.println(tasks.toString());
        System.out.println(SEPARATOR);
    }
    private static void printTaskDone(Task task) {
        System.out.print("    Hey, looks like you're done with this task:\n   ");
        System.out.println(task.toString());
        System.out.println(SEPARATOR);
    }

    private static void printTaskUndone(Task task) {
        System.out.print("    Oops, looks like you're still not done with this:\n   ");
        System.out.println(task.toString());
        System.out.println(SEPARATOR);
    }

    private static void printTaskAdded(Task task, int numTasks) {
        System.out.println("    Hey, I've added:\n    " + task.toString());
        String singularOrPlural = numTasks == 1 ? " task."  : " tasks.";
        System.out.println("    You currently have " + numTasks + singularOrPlural);
        System.out.println(SEPARATOR);
    }

    private static void printInvalidTaskNo() {
        System.out.println("    Sorry, that was an invalid task number.");
        System.out.println(SEPARATOR);
    }

    private static void printNoValueForFields() {
        System.out.println("    Error: no value for required fields");
        System.out.println(SEPARATOR);
    }

    private static void printNoSuchCommand() {
        System.out.println("    Sorry, I don't understand that command");
        System.out.println(SEPARATOR);
    }

    private static void printGoodbyeMessage() {
        System.out.println("    Bean will take a nap now. Bye!");
        System.out.println(SEPARATOR);
    }

    private static void processAndExecute(String line, TaskList listOfTasks) {
        Parser userLine = new Parser(line);
        if (userLine.getCommand().equals("list")) {
            printTaskList(listOfTasks);

        } else if (userLine.getCommand().startsWith("mark")) {
            int taskIndex = Integer.parseInt(userLine.getArgument()) - 1;
            if (taskIndex >= 0 && taskIndex < listOfTasks.getNumTasks()) {
                Task markedTask = listOfTasks.markTask(taskIndex, true);
                printTaskDone(markedTask);
            } else {
                printInvalidTaskNo();
            }

        } else if (userLine.getCommand().startsWith("unmark")) {
            int taskIndex = Integer.parseInt(userLine.getArgument()) - 1;
            if (taskIndex >= 0 && taskIndex < listOfTasks.getNumTasks()) {
                Task unmarkedTask = listOfTasks.markTask(taskIndex, false);
                printTaskUndone(unmarkedTask);
            } else {
                printInvalidTaskNo();
            }

        } else if (userLine.getCommand().equals("todo")) {
            String description = userLine.getArgument();
            if (description.isEmpty()) {
                printNoValueForFields();
            } else {
                Task newTask = listOfTasks.addTask(description);
                printTaskAdded(newTask, listOfTasks.getNumTasks());
            }

        } else if (userLine.getCommand().equals("deadline")) {
            String description = userLine.getArgument();
            String by = userLine.getValue("by");
            if (by.isEmpty() || description.isEmpty()) {
                printNoValueForFields();
            } else {
                Task newTask = listOfTasks.addTask(description, by);
                printTaskAdded(newTask, listOfTasks.getNumTasks());
            }

        } else if (userLine.getCommand().equals("event")) {
            String description = userLine.getArgument();
            String start = userLine.getValue("start");
            String end = userLine.getValue("end");
            if (start.isEmpty() || end.isEmpty() || description.isEmpty()) {
                printNoValueForFields();
            } else {
                Task newTask = listOfTasks.addTask(description, start, end);
                printTaskAdded(newTask, listOfTasks.getNumTasks());
            }

        } else {
            printNoSuchCommand();
        }
    }

    private static String getUserInput() {
        String inputLine = SCANNER.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty()) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine;
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        String line = getUserInput();
        TaskList listOfTasks = new TaskList();

        while (!line.equals("bye")) {
            processAndExecute(line, listOfTasks);
            line = getUserInput();
        }

        printGoodbyeMessage();
    }
}
