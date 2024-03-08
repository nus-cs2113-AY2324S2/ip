import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CacheManager {
    private static final String CACHE_FILE_PATH = "./data/duke.txt";
    private static final String CACHE_FILE_DIR = "./data";
    private static final String CACHE_FILE_DELIM = " \\| ";

    /**
     * Creates a file called "duke.txt" to cache user data in directory "data"
     * Creates a new directory called "data" if not created yet
     */
    public static void spawnCacheFile() {
        File f = new File(CACHE_FILE_PATH);
        boolean isSpawnSuccess = false;
        while (!isSpawnSuccess) {
            try {
                isSpawnSuccess = f.createNewFile();
            } catch (IOException e) {
                new File(CACHE_FILE_DIR).mkdirs();
            }
        }
    }

    /**
     * Reads user data from cache file and adds the tasks based on read data
     *
     * @throws IllegalArgumentException If zone is <= 0.
     */
    public static void bootFromCache() throws FileNotFoundException {
        File f = new File(CACHE_FILE_PATH);
        Scanner CacheReader = new Scanner(f);
        int lineNum = 0;

        while (CacheReader.hasNext()) {
            String curLine = CacheReader.nextLine();
            lineNum++;
            String[] tokens = curLine.split(CACHE_FILE_DELIM);
            switch (tokens[0]) {
            case "T":
                Todo newTask = new Todo(tokens[2]);
                if (tokens[1].equals("1")) {
                    newTask.markAsDone();
                }
                CommandExecutor.tasks.add(newTask);
                break;
            case "D":
                Deadline newDeadline = new Deadline(tokens[2], tokens[3]);
                if (tokens[1].equals("1")) {
                    newDeadline.markAsDone();
                }
                CommandExecutor.tasks.add(newDeadline);
                break;
            case "E":
                Event newEvent = new Event(tokens[2], tokens[3], tokens[4]);
                if (tokens[1].equals("1")) {
                    newEvent.markAsDone();
                }
                CommandExecutor.tasks.add(newEvent);
                break;
            default:
                Formatter.printFileCorruptionError(lineNum);
                break;
            }
        }
    }

    /**
     * Appends a new task to cache file
     *
     * @param task
     */
    public static void updateCache(Task task) {
        try {
            FileWriter fw = new FileWriter(CACHE_FILE_PATH, true);
            String formattedString = task.toString(true);
            fw.write(Formatter.appendNewLine(formattedString));
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Overwrites the entire cache file whenever tasks are marked, unmarked or deleted
     */
    public static void updateCache() {
        try {
            FileWriter fw = new FileWriter(CACHE_FILE_PATH);
            String formattedString;
            for (Task task : CommandExecutor.tasks) {
                formattedString = task.toString(true);
                fw.write(Formatter.appendNewLine(formattedString));
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
