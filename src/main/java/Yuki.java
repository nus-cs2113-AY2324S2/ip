import yuki.InputParser;
import yuki.Utils;
import yuki.task.Task;
import yuki.task.Todo;
import yuki.task.Deadline;
import yuki.task.Event;
import yuki.exceptions.YukiExceptions;

import java.util.Scanner;
public class Yuki {

    private static final int MAX_NUM_TASKS = 100;
    // length of each command: 'todo', 'deadline', 'event'
    private static final int LENGTH_TODO_COMMAND = 4;
    private static final int LENGTH_DEADLINE_COMMAND = 8;
    private static final int LENGTH_EVENT_COMMAND = 5;

    private static Task[] tasks = new Task[MAX_NUM_TASKS];
    private static int taskCount = 0;

    private static String[] data;
    private static String description;

    public static void listTasks() {
        System.out.println("Wake up your idea and do these tasks:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].taskType + " " + tasks[i].description);
        }
        reportNumberOfTasks();
    }

    public static void addTodo(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(LENGTH_TODO_COMMAND));
        if (data[0].isEmpty()) {
            throw new YukiExceptions.InvalidDescriptionException();
        }
        description = data[0];
        tasks[taskCount] = new Todo(description);
        taskCount++;
        reportNumberOfTasks();
    }

    public static void addDeadline(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(LENGTH_DEADLINE_COMMAND));
        if (data.length < 2) {
            throw new YukiExceptions.InvalidDescriptionException();
        }
        description = data[0] + " (by:" + data[1] + ")";
        tasks[taskCount] = new Deadline(description);
        taskCount++;
        reportNumberOfTasks();
    }

    public static void addEvent(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(LENGTH_EVENT_COMMAND));
        if (data.length < 3) {
            throw new YukiExceptions.InvalidDescriptionException();
        }
        description = data[0] + " (from: " + data[1] + " to: " + data[2] + ")";
        tasks[taskCount] = new Event(description);
        taskCount++;
        reportNumberOfTasks();
    }

    public static void reportNumberOfTasks() {
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void main(String[] args) {
        Utils.printWelcomeMessage();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        String command;
        int index_task;

        while (!line.equals("exit")) {
            Utils.printLine();
            command = line.split(" ")[0];

            switch(command) {
            case "list":
                listTasks();
                break;
            case "mark":
                index_task = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[index_task].markAsDone();
                break;
            case "unmark":
                index_task = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[index_task].markAsUndone();
                break;
            case "todo":
                try {
                    addTodo(line);
                } catch (YukiExceptions.InvalidDescriptionException e) {
                    Utils.printInvalidDescriptionWarning();
                    Utils.printInstructions();
                }
                break;
            case "deadline":
                try {
                    addDeadline(line);
                } catch (YukiExceptions.InvalidDescriptionException e) {
                    Utils.printInvalidDescriptionWarning();
                    Utils.printInstructions();
                }
                break;
            case "event":
                try {
                    addEvent(line);
                } catch (YukiExceptions.InvalidDescriptionException e) {
                    Utils.printInvalidDescriptionWarning();
                    Utils.printInstructions();
                }
                break;
            default:
                Utils.printInvalidCommandWarning();
                Utils.printInstructions();
                break;
            }

            Utils.printLine();
            line = in.nextLine();
        }
        Utils.printExitMessage();
    }
}
