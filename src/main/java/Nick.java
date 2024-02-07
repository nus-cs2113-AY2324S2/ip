import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;

public class Nick {
    public static int taskCount = 0;
    public static final int TO_OFFSET_IDX = 4;
    public static final int FROM_OFFSET_IDX = 6;
    public static final int DEADLINE_OFFSET_IDX = 4;
    private static final Logger LOGGER = Logger.getLogger(Nick.class.getName());

    public static void main(String[] args) {
        printIntroName();
        printIntroMsg();

        boolean hasQuit = false;
        String command, commandAction;
        Task[] userTasks = new Task[100];
        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {
            command = input.nextLine();
            commandAction = command.split(" ")[0];

            switch (commandAction) {
            case "bye":
            case "list":
            case "mark":
            case "unmark":
                boolean continueProgram = continueExecution(command, taskCount, userTasks);
                if (continueProgram) {
                    continue;
                }
                hasQuit = true;
            }
            if (hasQuit) {
                break;
            }

            taskExecution(command, commandAction, userTasks);
        }
    }

    public static void taskExecution(String command, String taskType, Task[] userTasks) {
        Task task;
        String taskName;
        int taskDescriptionIndex = taskType.length() + 1;
        int taskDescriptionEndIndex = command.indexOf("/") - 1;

        switch (taskType) {
        case "todo":
            taskName = command.substring(taskDescriptionIndex);
            task = new Todo(taskName);
            userTasks[taskCount] = task;
            break;
        case "deadline":
            int deadlineIndex = command.indexOf("/by") + DEADLINE_OFFSET_IDX;
            taskName = command.substring(taskDescriptionIndex, taskDescriptionEndIndex);
            String deadline = command.substring(deadlineIndex);
            task = new Deadline(taskName, deadline);
            userTasks[taskCount] = task;
            break;
        case "event":
            int fromIndex = command.indexOf("/from");
            int toIndex = command.indexOf("/to");
            taskName = command.substring(taskDescriptionIndex, taskDescriptionEndIndex);
            String from = command.substring(fromIndex + FROM_OFFSET_IDX, toIndex - 1);
            String to = command.substring(toIndex + TO_OFFSET_IDX);
            task = new Event(taskName, from, to);
            userTasks[taskCount] = task;
            break;
        }

        printAddTaskMsg(userTasks[taskCount].toString(), taskCount);
        taskCount++;
    }

    public static boolean continueExecution(String command, int taskCount, Task[] userTasks) {
        String commandAction = command.split(" ")[0];

        switch (commandAction) {
        case ("bye"):
            printByeMsg();
            return false;
        case ("list"):
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("\t" + Integer.toString(i + 1) + "." + userTasks[i]);
            }
            System.out.println("____________________________________________________________");
            break;
        case ("mark"):
            userTasks[Integer.parseInt(command.split(" ")[1]) - 1].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + "[" + userTasks[Integer.parseInt(command.split(" ")[1]) - 1].getStatusIcon() + "] " + userTasks[Integer.parseInt(command.split(" ")[1]) - 1].description);
            System.out.println("____________________________________________________________");
            break;
        case ("unmark"):
            userTasks[Integer.parseInt(command.split(" ")[1]) - 1].markAsUndone();
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("\t" + "[" + userTasks[Integer.parseInt(command.split(" ")[1]) - 1].getStatusIcon() + "] " + userTasks[Integer.parseInt(command.split(" ")[1]) - 1].description);
            System.out.println("____________________________________________________________");
            break;
        }

        return true;
    }

    public static void printByeMsg() {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void printAddTaskMsg(String taskName, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t" + taskName);
        System.out.println("\t" + "Now you have " + (taskCount + 1) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printIntroName() {
        try {
            String name = new String(Files.readAllBytes(Paths.get("name.txt")));
            System.out.print(name);
        } catch (IOException exception) {
            LOGGER.severe(exception.toString());
        }
    }

    public static void printIntroMsg() {
        System.out.println("____________________________________________________________");
        System.out.println("Welcome to the Ultimate Nick Bot!");
        System.out.println("What can I do for you?\n");
    }
}
