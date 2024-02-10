import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;

public class Nick {
    public static int taskCount = 0;
    public static final int TOTAL_TASKS = 100;
    public static final int TO_OFFSET_IDX = 4;
    public static final int FROM_OFFSET_IDX = 6;
    public static final int DEADLINE_OFFSET_IDX = 4;
    public static final String FORMAT_LINES = "____________________________________________________________";
    private static final Logger LOGGER = Logger.getLogger(Nick.class.getName());

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.printIntroMsg();

        boolean hasQuit = false;
        String command, commandAction;
        Task[] userTasks = new Task[TOTAL_TASKS];
        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {
            command = input.nextLine();
            commandAction = command.split(" ")[0];

            switch (commandAction) {
            case "bye":
                // Fallthrough
            case "list":
                // Fallthrough
            case "mark":
                // Fallthrough
            case "unmark":
                boolean isContinueProgram = continueExecution(command, taskCount, userTasks, ui);
                if (isContinueProgram) {
                    continue;
                }
                hasQuit = true;
            }
            if (hasQuit) {
                break;
            }

            taskExecution(command, commandAction, userTasks, ui);
        }
    }

    public static void taskExecution(String command, String taskType, Task[] userTasks, UserInterface ui) {
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

        ui.printAddTaskMsg(userTasks[taskCount].toString(), taskCount);
        taskCount++;
    }

    public static boolean continueExecution(String command, int taskCount, Task[] userTasks, UserInterface ui) {
        String commandAction = command.split(" ")[0];

        switch (commandAction) {
        case ("bye"):
            ui.printByeMsg();
            return false;
        case ("list"):
            System.out.println(FORMAT_LINES);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("\t" + Integer.toString(i + 1) + "." + userTasks[i]);
            }
            System.out.println(FORMAT_LINES);
            break;
        case ("mark"):
            userTasks[Integer.parseInt(command.split(" ")[1]) - 1].markAsDone();
            System.out.println(FORMAT_LINES);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" +
                    "[" +
                    userTasks[Integer.parseInt(command.split(" ")[1]) - 1].getStatusIcon() +
                    "] " +
                    userTasks[Integer.parseInt(command.split(" ")[1]) - 1].description);
            System.out.println(FORMAT_LINES);
            break;
        case ("unmark"):
            userTasks[Integer.parseInt(command.split(" ")[1]) - 1].markAsUndone();
            System.out.println(FORMAT_LINES);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("\t" +
                    "[" +
                    userTasks[Integer.parseInt(command.split(" ")[1]) - 1].getStatusIcon() +
                    "] " +
                    userTasks[Integer.parseInt(command.split(" ")[1]) - 1].description);
            System.out.println(FORMAT_LINES);
            break;
        }

        return true;
    }
}
