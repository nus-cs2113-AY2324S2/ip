package yuki;

import yuki.data.Storage;
import yuki.exceptions.YukiExceptions;
import yuki.task.Deadline;
import yuki.task.Event;
import yuki.task.Task;
import yuki.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;

public class Yuki {

//    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static String[] data;
    private static String description;

    private static Storage storage = new Storage("data/tasks.txt");
    private static ArrayList<Task> tasksData = storage.loadData();


    public static void listTasks() {
        System.out.println("Wake up your idea and do these tasks:");
        int index = 1;
        for (Task item : tasksData) {
            System.out.println((index) + ".[" + item.getStatusIcon() + "] "
                    + item.taskType + " " + item.description);
            index++;
        }
        reportNumberOfTasks();
    }

    public static void markTask(String line, String cmd) throws YukiExceptions.InvalidIndexException {
        int indexTask = Integer.parseInt(line.split(" ")[1]);
        if (indexTask < 0 || indexTask > tasksData.size()) {
            throw new YukiExceptions.InvalidIndexException("Invalid index for marking: " + indexTask);
        }
        if (cmd.equals(Constants.MARK_COMMAND)){
            tasksData.get(indexTask - 1).markAsDone();
        } else if (cmd.equals(Constants.UNMARK_COMMAND)) {
            tasksData.get(indexTask - 1).markAsUndone();
        } else {
            System.out.println("invalid command in mark method");
        }
    }

    public static void deleteTask(String line) throws YukiExceptions.InvalidIndexException {
        int indexTask = Integer.parseInt(line.split(" ")[1]);
        if (indexTask < 0 || indexTask > tasksData.size()) {
            throw new YukiExceptions.InvalidIndexException("Invalid index for marking: " + indexTask);
        }
        Task toDelete = tasksData.get(indexTask - 1);
        tasksData.remove(indexTask - 1);
        System.out.println("Deleted task number " + (indexTask) + ": " + toDelete.description);
    }

    public static void addTodo(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(Constants.LENGTH_TODO_COMMAND));
        if (data[0].isEmpty()) {
            throw new YukiExceptions.InvalidDescriptionException("Invalid description");
        }
        description = data[0];
        Task t = new Todo(description);
        tasksData.add(t);
        System.out.println(t);
        reportNumberOfTasks();
    }

    public static void addDeadline(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(Constants.LENGTH_DEADLINE_COMMAND));
        if (data.length < 2) {
            throw new YukiExceptions.InvalidDescriptionException("Invalid description");
        }
        description = data[0] + " (by:" + data[1] + ")";
        Task t = new Deadline(description);
        tasksData.add(t);
        System.out.println(t);
        reportNumberOfTasks();
    }

    public static void addEvent(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(Constants.LENGTH_EVENT_COMMAND));
        if (data.length < 3) {
            throw new YukiExceptions.InvalidDescriptionException("Invalid description");
        }
        description = data[0] + " (from: " + data[1] + " to: " + data[2] + ")";
        Task t = new Event(description);
        tasksData.add(t);
        System.out.println(t);
        reportNumberOfTasks();
    }

    public static void reportNumberOfTasks() {
        System.out.println("Now you have " + tasksData.size() + " tasks in the list.");
    }

    public static void main(String[] args) throws IOException {

        Utils.printWelcomeMessage();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        String command;

        while (!line.equals(Constants.EXIT_COMMAND)) {
            Utils.printLine();
            command = line.split(" ")[0];

            switch(command) {
            case Constants.LIST_COMMAND:
                listTasks();
                break;
            case Constants.MARK_COMMAND:
                try {
                    markTask(line, Constants.MARK_COMMAND);
                } catch (YukiExceptions.InvalidIndexException e) {
                    System.out.println(e.getMessage() + "\nPlease enter a valid index\n");
                    listTasks();
                }
                break;
            case Constants.UNMARK_COMMAND:
                try {
                    markTask(line, Constants.UNMARK_COMMAND);
                } catch (YukiExceptions.InvalidIndexException e) {
                    System.out.println(e.getMessage() + "\nPlease enter a valid index\n");
                    listTasks();
                }
                break;
            case Constants.DELETE_COMMAND:
                try {
                    deleteTask(line);
                } catch (YukiExceptions.InvalidIndexException e) {
                    System.out.println(e.getMessage() + "\nPlease enter a valid index\n");
                    listTasks();
                }
                break;
            case Constants.TODO_COMMAND:
                try {
                    addTodo(line);
                } catch (YukiExceptions.InvalidDescriptionException e) {
                    System.out.println(e.getMessage() + "\nPlease enter a valid description\n");
                    Utils.printInstructions();
                }
                break;
            case Constants.DEADLINE_COMMAND:
                try {
                    addDeadline(line);
                } catch (YukiExceptions.InvalidDescriptionException e) {
                    System.out.println(e.getMessage() + "\nPlease enter a valid description\n");
                    Utils.printInstructions();
                }
                break;
            case Constants.EVENT_COMMAND:
                try {
                    addEvent(line);
                } catch (YukiExceptions.InvalidDescriptionException e) {
                    System.out.println(e.getMessage() + "\nPlease enter a valid description\n");
                    Utils.printInstructions();
                }
                break;
            case Constants.HELP_COMMAND:
                Utils.printInstructions();
            default:
                Utils.printInvalidCommandWarning();
                Utils.printInstructions();
                break;
            }

            Utils.printLine();
            line = in.nextLine();
        }
        Storage.writeFile(tasksData);
        Utils.printExitMessage();
    }
}
