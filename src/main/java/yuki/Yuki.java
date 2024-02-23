package yuki;

import yuki.exceptions.YukiExceptions;
import yuki.task.Deadline;
import yuki.task.Event;
import yuki.task.Task;
import yuki.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import static yuki.Constants.TODO_INDICATOR;
import static yuki.Constants.DEADLINE_INDICATOR;
import static yuki.Constants.EVENT_INDICATOR;

public class Yuki {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static String[] data;
    private static String description;

    private static final String PATH_TO_FILE = "data/tasks.txt";

    public static void readFile() throws FileNotFoundException {
        File inputFile = new File(PATH_TO_FILE);
        Scanner inputScanner = new Scanner(inputFile);
        String line;
        String taskDescription;
        while (inputScanner.hasNext()) {
            line = inputScanner.nextLine();
            taskDescription = line.substring(10);
            if (line.charAt(7) == TODO_INDICATOR) {
                Task t = new Todo(taskDescription);
                tasks.add(t);
            } else if (line.charAt(7) == DEADLINE_INDICATOR) {
                Task t = new Deadline(taskDescription);
                tasks.add(t);
            } else if (line.charAt(7) == EVENT_INDICATOR) {
                Task t = new Event(taskDescription);
                tasks.add(t);
            } else {
                System.out.println("error in input file");
            }
        }
    }

    public static void writeFile() throws IOException {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            boolean hasDirectoryCreated = dataDirectory.mkdirs(); // create directory to save in.
            if (!hasDirectoryCreated) {
                // Handle the case where directory creation fails
                throw new IOException("Failed to create the 'data' directory.");
            }
        }

        FileWriter fw = new FileWriter(PATH_TO_FILE);

        int index = 1;
        for (Task item : tasks) {
            fw.write((index) + ".[" + item.getStatusIcon() + "] "
                    + item.taskType + " " + item.description + "\n");
            index++;
        }
        fw.close();
    }

    public static void listTasks() {
        System.out.println("Wake up your idea and do these tasks:");
        int index = 1;
        for (Task item : tasks) {
            System.out.println((index) + ".[" + item.getStatusIcon() + "] "
                    + item.taskType + " " + item.description);
            index++;
        }
        reportNumberOfTasks();
    }

    public static void markTask(String line, String cmd) throws YukiExceptions.InvalidIndexException {
        int indexTask = Integer.parseInt(line.split(" ")[1]);
        if (indexTask < 0 || indexTask > tasks.size()) {
            throw new YukiExceptions.InvalidIndexException("Invalid index for marking: " + indexTask);
        }
        if (cmd.equals(Constants.MARK_COMMAND)){
            tasks.get(indexTask - 1).markAsDone();
        } else if (cmd.equals(Constants.UNMARK_COMMAND)) {
            tasks.get(indexTask - 1).markAsUndone();
        } else {
            System.out.println("invalid command in mark method");
        }
    }

    public static void deleteTask(String line) throws YukiExceptions.InvalidIndexException {
        int indexTask = Integer.parseInt(line.split(" ")[1]);
        if (indexTask < 0 || indexTask > tasks.size()) {
            throw new YukiExceptions.InvalidIndexException("Invalid index for marking: " + indexTask);
        }
        Task toDelete = tasks.get(indexTask - 1);
        tasks.remove(indexTask - 1);
        System.out.println("Deleted task number " + (indexTask) + ": " + toDelete.description);
    }

    public static void addTodo(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(Constants.LENGTH_TODO_COMMAND));
        if (data[0].isEmpty()) {
            throw new YukiExceptions.InvalidDescriptionException("Invalid description");
        }
        description = data[0];
        Task t = new Todo(description);
        tasks.add(t);
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
        tasks.add(t);
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
        tasks.add(t);
        System.out.println(t);
        reportNumberOfTasks();
    }

    public static void reportNumberOfTasks() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        try {
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("Txt file of tasks not found, will create one");
        }

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
        try {
            writeFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        Utils.printExitMessage();
    }
}
