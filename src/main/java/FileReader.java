import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that reads the tasks stored in local storage.
 *
 * @param path Relative file path of the file.
 * @param tasks List of tasks loaded.
 */
public class FileReader {
    protected String filePath;
    protected ArrayList<Task> tasks;

    public FileReader (String path) {
        this.filePath = path;
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns task list after looping over all the lines in the .txt file storage is read and tasks are created.
     *
     * @throws IOException If file cannot be read.
     * @throws DirectoryCannotBeMadeException If Directory cannot be made on local.
     */
    public ArrayList<Task> getTasks() throws IOException, DirectoryCannotBeMadeException {
        // Reads the txt file to get the tasks stored in local
        File f = new File(filePath);
        if (!f.exists()) {
            createNewFile();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] taskSegments = line.split("\\|"); // Split based of |
            if (taskSegments[0].equalsIgnoreCase("T")) { // Checks task type
                Task task = new Todo(taskSegments[2], taskSegments[0]);
                if (taskSegments[1].equals("1")) {
                    task.markDone();
                }
                tasks.add(task);
            } else if (taskSegments[0].equalsIgnoreCase("D")) {
                Task task = new Deadline(taskSegments[2], LocalDateTime.parse(taskSegments[3]), taskSegments[0]);
                if (taskSegments[1].equals("1")) {
                    task.markDone();
                }
                tasks.add(task);
            } else {
                Task task = new Event(taskSegments[2], LocalDateTime.parse(taskSegments[3]), LocalDateTime.parse(taskSegments[4]), taskSegments[0]);
                if (taskSegments[1].equals("1")) {
                    task.markDone();
                }
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Creates a new file if there is no storage file detected.
     *
     * @throws DirectoryCannotBeMadeException If Directory cannot be made on local.
     * @throws IOException If file cannot be read.
     */
    public void createNewFile() throws DirectoryCannotBeMadeException, IOException {
        // Creates a new file in the desired file path
        boolean isDirMade = false;
        boolean isFileMade = false;
        File directory = new File("./data");
        if (!directory.exists()) {
            isDirMade = directory.mkdir();
        }
        if (!isDirMade) {
            throw new DirectoryCannotBeMadeException("Data directory could not be made");
        }
        File tasksData = new File(filePath);
        if (!tasksData.exists()) {
            isFileMade = tasksData.createNewFile();
        }
    }
}
