import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static String FILE_PATH = "./miku.txt";


    public Storage() {
        saveToFile();
    }

    /**
     * Handles the process of overwriting the current data in the text file
     * and writing to it with the current list to save the data
     *
     */
    public static void saveToFile() {
        if (TaskList.numberOfListItems > 0) {
            writeToFile(TaskList.storedList.get(0).saveTaskDescription() + System.lineSeparator());
            for (int i = 1; i < TaskList.numberOfListItems; i++) {
                appendToFile(TaskList.storedList.get(i).saveTaskDescription() + System.lineSeparator());
            }
        }
    }

    /**
     * Overwrites the current data in the saved text file with one task.
     * If there is no existing directory/text file, it creates one
     *
     * @param textToAdd The String of the first task in the list to be saved in the text file
     */
    public static void writeToFile(String textToAdd) {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter f = new FileWriter(FILE_PATH, false);
            f.write(textToAdd);
            f.close();
        } catch (IOException e) {
            Ui.fileError();
        }
    }


    /**
     * Appends to the current data in the saved text file with a task
     *
     * @param textToAppend The String of the task in the list to be saved in the text file
     */
    public static void appendToFile(String textToAppend) {
        try {
            FileWriter f = new FileWriter(FILE_PATH, true);
            f.write(textToAppend);
            f.close();
        } catch (IOException e) {
            Ui.fileError();
        }
    }

    /**
     * Loads the data from the saved file
     *
     * @return loadedTask The list of tasks containing all tasks that had been saved in the text file
     * @throws FileNotFoundException If the file does not exist
     */
    public static ArrayList<Task> loadData() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        ArrayList<Task> loadedTasks = new ArrayList<>();

        int currentLoadListIndex = 0;
        while (s.hasNextLine()) {
            String[] command = s.nextLine().split("\\|");
            transferLoadToArray(command, loadedTasks, currentLoadListIndex);
            currentLoadListIndex++;
        }

        return loadedTasks;
    }

    /**
     * Rewrites each task from the load data into a format understandable by other classes.
     *
     * @param command The String array of a task loaded from the text file
     * @param loadedTasks The list of tasks containing tasks that had been saved in the text file
     * @param currentLoadListIndex The current index of the loaded list
     */
    public static void transferLoadToArray(String[] command, ArrayList<Task> loadedTasks, int currentLoadListIndex) {
        boolean isMarked = (command[1].equals("1"));
        String loadedTaskDescription;

        switch (command[0]) {
        case "T":
            loadedTaskDescription = (command[2]);
            try {
                TaskList.newTodo("todo " + loadedTaskDescription, loadedTasks);
                loadedTasks.get(currentLoadListIndex).isDone = isMarked;
            } catch (MikuException e) {
                Ui.loadError();
            }
            break;
        case "D":
            loadedTaskDescription = ("deadline " + command[2] + "/by " + command[3]);
            try {
                TaskList.newDeadline(loadedTaskDescription, loadedTasks);
                loadedTasks.get(currentLoadListIndex).isDone = isMarked;
            } catch (MikuException e) {
                Ui.loadError();
            } catch (wrongDeadlineArguments e) {
                Ui.loadArgumentsError();
            }
            break;
        case "E":
            loadedTaskDescription = ("event " + command[2] + "/from " + command[3] + "/to " + command[4]);
            try {
                TaskList.newEvent(loadedTaskDescription, loadedTasks);
                loadedTasks.get(currentLoadListIndex).isDone = isMarked;
            } catch (MikuException e) {
                Ui.loadError();
            } catch (wrongEventArguments e) {
                Ui.loadArgumentsError();
            }
            break;
        default:
            Ui.loadError();
            break;
        }
    }

}