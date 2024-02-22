import command.Deadline;
import command.Event;
import command.Task;
import command.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Nick {
    public static int taskCount = 0;
    public static final int TOTAL_TASKS = 100;
    public static final int TO_OFFSET_IDX = 4;
    public static final int FROM_OFFSET_IDX = 6;
    public static final int DEADLINE_OFFSET_IDX = 4;
    public static final String FORMAT_LINES = "____________________________________________________________";
    private static final Logger LOGGER = Logger.getLogger(Nick.class.getName());

    public static void main(String[] args) throws NickException {
        UserInterface ui = new UserInterface();
        ui.printIntroMsg();

        boolean hasQuit = false;
        String command, commandAction;
        Task[] userTasks = new Task[TOTAL_TASKS];
        Scanner input = new Scanner(System.in);

        try {
            File f = new File("data/nick.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String lineData = s.nextLine();
                String taskType =  lineData.split(" ")[0];
                loadData(lineData, taskType, userTasks);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }

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

    public static void taskExecution(String command, String taskType, Task[] userTasks, UserInterface ui) throws NickException {
        Task task;
        String taskName;
        int taskDescriptionIndex = taskType.length() + 1;
        int taskDescriptionEndIndex = command.indexOf("/") - 1;

        switch (taskType) {
        case "todo":
            try {
                taskName = command.substring(taskDescriptionIndex);
                task = new Todo(taskName);
                userTasks[taskCount] = task;
                ui.printAddTaskMsg(userTasks[taskCount].toString(), taskCount);
                taskCount++;
                saveData(userTasks);
                break;
            }
            catch (StringIndexOutOfBoundsException exception) {
                System.out.println(FORMAT_LINES + System.lineSeparator() +
                        "I cannot add a command.Todo task which has no description. " + System.lineSeparator() +
                        "Try adding the task description after specifying the command.Todo command!");
                break;
            }
        case "deadline":
            int deadlineIndex = command.indexOf("/by") + DEADLINE_OFFSET_IDX;
            taskName = command.substring(taskDescriptionIndex, taskDescriptionEndIndex);
            String deadline = command.substring(deadlineIndex);
            task = new Deadline(taskName, deadline);
            userTasks[taskCount] = task;
            ui.printAddTaskMsg(userTasks[taskCount].toString(), taskCount);
            taskCount++;
            saveData(userTasks);
            break;
        case "event":
            int fromIndex = command.indexOf("/from");
            int toIndex = command.indexOf("/to");
            taskName = command.substring(taskDescriptionIndex, taskDescriptionEndIndex);
            String from = command.substring(fromIndex + FROM_OFFSET_IDX, toIndex - 1);
            String to = command.substring(toIndex + TO_OFFSET_IDX);
            task = new Event(taskName, from, to);
            userTasks[taskCount] = task;
            ui.printAddTaskMsg(userTasks[taskCount].toString(), taskCount);
            taskCount++;
            saveData(userTasks);
            break;
        default:
            try {
                throw new NickException();
            }
            catch (NickException exception) {
                System.out.println(FORMAT_LINES + System.lineSeparator() +
                        "Invalid command!!! Please try again.");
            }
        }

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

    public static void loadData(String command, String taskType, Task[] userTasks) {
        Task task;
        String taskName;
        int taskDescriptionIndex = taskType.length() + 7;
        int taskDescriptionEndIndex = command.indexOf("/") - 1;
        boolean taskDone = command.contains("1");

        switch (taskType) {
        case "todo":
            try {
                taskName = command.substring(taskDescriptionIndex);
                task = new Todo(taskName, taskDone);
                userTasks[taskCount] = task;
                taskCount++;
                break;
            }
            catch (StringIndexOutOfBoundsException exception) {
                System.out.println(FORMAT_LINES + System.lineSeparator() +
                        "I cannot add a command.Todo task which has no description. " + System.lineSeparator() +
                        "Try adding the task description after specifying the command.Todo command!");
                break;
            }
        case "deadline":
            int deadlineIndex = command.indexOf("/by") + DEADLINE_OFFSET_IDX;
            taskName = command.substring(taskDescriptionIndex, taskDescriptionEndIndex);
            String deadline = command.substring(deadlineIndex);
            task = new Deadline(taskName, deadline, taskDone);
            userTasks[taskCount] = task;
            taskCount++;
            break;
        case "event":
            int fromIndex = command.indexOf("/from");
            int toIndex = command.indexOf("/to");
            taskName = command.substring(taskDescriptionIndex, taskDescriptionEndIndex);
            String from = command.substring(fromIndex + FROM_OFFSET_IDX, toIndex - 1);
            String to = command.substring(toIndex + TO_OFFSET_IDX);
            task = new Event(taskName, from, to, taskDone);
            userTasks[taskCount] = task;
            taskCount++;
            break;
        default:
            try {
                throw new NickException();
            }
            catch (NickException exception) {
                System.out.println(FORMAT_LINES + System.lineSeparator() +
                        "Invalid command!!! Please try again.");
            }
        }
    }

    public static void saveData(Task[] userTasks) {
        try {
            new FileWriter("data/nick.txt", false).close();
            FileWriter fw = new FileWriter("data/nick.txt", true);
            for (int i = 0; i < taskCount; i++) {
                System.out.println("\t" + Integer.toString(i + 1) + "." + userTasks[i]);
                String done = (userTasks[i].getDoneStatus()) ? "1" : "0";
                if (userTasks[i] instanceof Todo) {
                    fw.write("todo " + "| " + done + " | " + userTasks[i].description + "\n");
                }
                else if (userTasks[i] instanceof Deadline) {
                    fw.write("deadline " + "| " + done + " | " + userTasks[i].description + " /by " + ((Deadline) userTasks[i]).getDeadline() + "\n");
                }
                else if (userTasks[i] instanceof Event) {
                    fw.write("event " + "| " + done + " | " + userTasks[i].description + " /from " + ((Event) userTasks[i]).getFrom() + " /to " + ((Event) userTasks[i]).getTo() + "\n");
                }
            }
            fw.close();
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}