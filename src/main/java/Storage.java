import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static String FILE_PATH = "./data/miku.txt";


    public Storage() {
        saveToFile();
    }

    public static void saveToFile() {
        if (TaskList.numberOfListItems > 0) {
            writeToFile(FILE_PATH, TaskList.storedList.get(0).saveTaskDescription() + System.lineSeparator());
            for (int i = 1; i < TaskList.numberOfListItems; i++) {
                appendToFile(FILE_PATH, TaskList.storedList.get(i).saveTaskDescription() + System.lineSeparator());
            }
        }
    }

    public static void writeToFile(String filePath, String textToAdd) {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter f = new FileWriter(filePath, false);
            f.write(textToAdd);
            f.close();
        } catch (IOException e) {
            Ui.fileError();
        }
    }

    public static void appendToFile(String filePath, String textToAppend) {
        try {
            FileWriter f = new FileWriter(filePath, true);
            f.write(textToAppend);
            f.close();
        } catch (IOException e) {
            Ui.fileError();
        }
    }

    public static ArrayList<Task> loadData() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        ArrayList<Task> loadedTasks = new ArrayList<>();

        int i = 0;
        while (s.hasNextLine()) {
            String[] command = s.nextLine().split("\\|");
            transferLoadToArray(command, loadedTasks, i);
            i++;
        }

        return loadedTasks;
    }

    public static void transferLoadToArray(String[] command, ArrayList<Task> loadedTasks, int i) {
        boolean isMarked = (command[1].equals("1"));
        String loadedTaskDescription;

        switch (command[0]) {
        case "T":
            loadedTaskDescription = (command[2]);
            try {
                TaskList.newTodo("todo " + loadedTaskDescription, loadedTasks);
                loadedTasks.get(i).isDone = isMarked;
            } catch (MikuException e) {
                Ui.loadError();
            }
            break;
        case "D":
            loadedTaskDescription = ("deadline " + command[2] + "/by " + command[3]);
            try {
                TaskList.newDeadline(loadedTaskDescription, loadedTasks);
                loadedTasks.get(i).isDone = isMarked;
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
                loadedTasks.get(i).isDone = isMarked;
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