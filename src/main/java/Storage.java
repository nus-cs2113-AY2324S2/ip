import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements a storage system that automatically saves task details
 * entered by the user. And loads past saves on application start.
 *
 * @author nigelheng
 * @since February 2024
 * @version 1.0
 */
public class Storage {

    private static final String LINE = "____________________________________________________________";
    private static final String SAVE_DIR_PATH = "src/storage/";
    private static final String FULL_FILE_PATH = "src/storage/saveFile.txt";
    private static final File FILE = new File(FULL_FILE_PATH);
    private static File directory = new File(SAVE_DIR_PATH);

    private static ArrayList<Task> allTasks = TaskList.getAllTasks();

    public Storage() {

    }

    /**
     * Loads task data from a saved file or creates a new save file if not found.
     */
    public static void loadFile() {
        try {
            System.out.println("Loading from saved file");
            Storage.loadFileContents(FILE);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Generating new save file.");
            Storage.createNewSaveFile();
        }
    }

    /**
     * Loads task data from a file and adds it to the task list.
     *
     * @param file The file containing the task data to be loaded.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static void loadFileContents(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            addToList(fileScanner.nextLine());
        }

        UI.showList();
    }

    /**
     * Creates a new save file for storing task data if it doesn't already exist.
     * It checks if the directory and the file itself exist. If not, it creates them.
     */
    public static void createNewSaveFile() {
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!FILE.exists()) {
                FILE.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e + "has occurred");
        }
        System.out.println("Type a command to begin");
        System.out.println(LINE);
    }

    private static String generateLine(int taskIndex) {
        Task taskToSave = allTasks.get(taskIndex);
        int taskMarkedStatus = (taskToSave.getStatus()) ? 1 : 0;
        String taskDescription = taskToSave.getDescription();
        return String.format("%d|%d|%s\n", taskIndex, taskMarkedStatus, taskDescription);
    }

    /**
     * Saves the current task list to a file.
     * Generates a string representation of the current task list,
     * then writes this data to a file specified.
     */
    public static void saveFile() {
        StringBuilder fileContents = new StringBuilder();
        String lineToAdd;
        int numberOfTasks = TaskList.getNumberOfTasks();

        for (int taskIndex = 0; taskIndex < numberOfTasks; taskIndex += 1) {
            lineToAdd = generateLine(taskIndex);
            fileContents.append(lineToAdd);
        }

        try {
            FileWriter fw = new FileWriter(FULL_FILE_PATH);
            fw.write(fileContents.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred when writing to the file: " + e);
        }
    }

    private static void loadStatusMarks(int taskNum, boolean isMarked) {
        if (isMarked) {
            allTasks.get(taskNum).markAsDone();
        }
    }

    private static void addToList(String line) {
        String[] taskItems = line.split("\\|", -1);
        int taskNum = Integer.parseInt(taskItems[0]);
        boolean isMarked = Integer.parseInt(taskItems[1]) == 1;
        String taskDetails = taskItems[2];

        boolean isLoad = true;

        TaskList.addTask(taskDetails, isLoad);
        loadStatusMarks(taskNum, isMarked);

    }
}