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
        loadData();
        printWelcomeMessage();

        while (true) {
            String userInput = getUserInput();
            if (hasSaidBye(userInput)) {
                System.out.println(LINE);
                return;
            } else if (hasSaidList(userInput)) {
                TaskList.printList(listTasks);
            } else {
                TaskList.handleUserTasks(userInput, listTasks);
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
                String parsedPreviousTask = Parser.parsePreviousTask(text);
                previousTasks.add(parsedPreviousTask);
                boolean isMarked = checkTasksMarkings(text);
                if (isMarked) {
                    previousTasks.add("mark " + taskIndex);
                }
                taskIndex += 1;
            }
        }
        for (String previousTask : previousTasks) {
            TaskList.handleUserTasks(previousTask, listTasks);
        }
        load.close();
    }

    private static boolean checkTasksMarkings (String text) {
        return text.contains("[X]");
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

    private static void printWelcomeMessage() {
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

    private static String getUserInput() {
        return in.nextLine();
    }

    private static void startMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Gandalf, your favorite personal assistant.");
        System.out.println("Please wait while I load your previous To-Do List.");
    }

    private static void endMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        in.close();
    }
}
