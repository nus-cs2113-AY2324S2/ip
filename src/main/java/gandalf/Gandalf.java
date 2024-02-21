package gandalf;

import action.Task;
import exception.FileEmptyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Gandalf {
    // Constants
    public static final String LINE = "____________________________________________________________";
    public static final String BYE_STATEMENT = "bye";
    public static final String MAKE_LIST_STATEMENT = "make list";

    // Task lists object
    static ArrayList<Task> listTasks = new ArrayList<>();
    static ArrayList<String> previousTasks = new ArrayList<>();

    // Scanner object
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        startMessage();
        startProgram();
        saveTasks();
        endMessage();
    }

    private static void startProgram() {
        while (true) {
            String userInput = getUserInput();
            if (hasSaidBye(userInput)) {
                System.out.println(LINE);
                return;
            }
            else if (isMakeList(userInput)) {
                makeList();
                return;
            } else {
                echoMessage(userInput);
            }
            System.out.println(LINE);
        }
    }

    private static void makeList() {

        loadData();
        makeListWelcomeMessage();

        while (true) {
            String userInput = getUserInput();
            if (hasSaidBye(userInput)) {
                System.out.println(LINE);
                return;
            } else if (hasSaidList(userInput)) {
                TaskManager.printList(listTasks);
            } else {
                TaskManager.handleUserTasks(userInput, listTasks);
            }
        }
    }

    private static void loadData() {
        try {
            System.out.println(LINE);
            System.out.println("Loading previous To-Do List....");
            loadTasks("data/savefile.txt");
        } catch (FileNotFoundException e) {
            System.out.println(LINE);
            System.out.println("File not found. You may start creating your new list.");
        } catch (FileEmptyException e) {
            System.out.println(LINE);
            System.out.println("No save data found. You may start creating your new list.");
        }
    }

    private static void loadTasks(String filePath)
            throws FileEmptyException, FileNotFoundException {
        File saveFile = new File(filePath);
        Scanner load = new Scanner(saveFile);
        if (!load.hasNext()) {
            throw new FileEmptyException();
        }
        else {
            int taskIndex = 1;
            while (load.hasNext()) {
                String text = load.nextLine();
                String parsedTask = parseTask(text);
                previousTasks.add(parsedTask);
                boolean marked = checkTasksMarkings(text);
                if (marked) {
                    previousTasks.add("mark " + taskIndex);
                }
                taskIndex += 1;
            }
        }
        for (String previousTask : previousTasks) {
            TaskManager.handleUserTasks(previousTask, listTasks);
        }
        load.close();
    }

    private static boolean checkTasksMarkings (String text) {
        return text.contains("[X]");
    }

    private static String parseTask(String line) {
        String parsedTask = null;
        if (line.startsWith("[T]")) {
            // Todo task
            parsedTask = "todo " + line.substring(6).trim();
        } else if (line.startsWith("[D]")) {
            // Deadline task
            int endIndex = line.indexOf("(by:");
            String description = line.substring(6, endIndex).trim();
            String deadline = line.substring(endIndex + 5, line.length() - 1).trim();
            parsedTask = "deadline " + description + " /by " + deadline;
        } else if (line.startsWith("[E]")) {
            // Event task
            int fromIndex = line.indexOf("(from:") + 6;
            int toIndex = line.indexOf("to:");
            String description = line.substring(6, fromIndex - 7).trim();
            String fromTime = line.substring(fromIndex, toIndex - 1).trim();
            String toTime = line.substring(toIndex + 4, line.length() - 1).trim();
            parsedTask = "event " + description + " /from " + fromTime + " /to " + toTime;
        }
        return parsedTask;
    }

    private static void saveTasks() {
        try {
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                if (dataDir.mkdirs()) {
                    System.out.println("Data directory created successfully.");
                } else {
                    System.err.println("Failed to create data directory.");
                    return;
                }
            }

            String filePath = "./data/savefile.txt";
            FileWriter writer = new FileWriter(filePath);
            String concatenatedData = compileData();
            writer.write(concatenatedData);
            writer.close();

            System.out.println("Content has been saved to the file successfully.");

        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private static String compileData() {
        StringBuilder dataToSave = new StringBuilder();
        for (Task listTask : listTasks) {
            if (listTask != null) {
                dataToSave.append(listTask);
                dataToSave.append("\n");
            }
        }
        return dataToSave.toString();
    }

    private static void makeListWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("What would you like to be added to the list?");
        System.out.println(LINE);
    }

    private static boolean hasSaidList(String userInput) {
        return userInput.equals("list");
    }

    private static boolean hasSaidBye(String text) {
        return text.equals(BYE_STATEMENT);
    }

    private static boolean isMakeList(String text) {
        return text.equals(MAKE_LIST_STATEMENT);
    }

    private static String getUserInput() {
        return in.nextLine();
    }

    private static void startMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Gandalf");
        System.out.println("What can I do for you? I'll start by repeating your words.");
        System.out.println("Type (make list) to create a To-Do List.");
        System.out.println(LINE);
    }

    private static void echoMessage(String text) {
        System.out.println(LINE);
        System.out.println(text);
    }

    private static void endMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        in.close();
    }
    //testing
}
