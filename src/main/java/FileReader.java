import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    protected String filePath;
    protected ArrayList<Task> tasks;

    public FileReader (String path) {
        this.filePath = path;

        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() throws IOException, DirectoryCannotBeMadeException {
        File f = new File(filePath);
        if (!f.exists()) {
            createNewFile();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] taskSegments = line.split("\\|");
            if (taskSegments[0].equalsIgnoreCase("T")) {
                Task task = new Todo(taskSegments[2], taskSegments[0]);
                if (taskSegments[1].equals("1")) {
                    task.markDone();
                }
                tasks.add(task);
            } else if (taskSegments[0].equalsIgnoreCase("D")) {
                Task task = new Deadline(taskSegments[2], taskSegments[3], taskSegments[0]);
                if (taskSegments[1].equals("1")) {
                    task.markDone();
                }
                tasks.add(task);
            } else {
                Task task = new Event(taskSegments[2], taskSegments[3], taskSegments[4], taskSegments[0]);
                if (taskSegments[1].equals("1")) {
                    task.markDone();
                }
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void createNewFile() throws DirectoryCannotBeMadeException, IOException {
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

    public void makeFile() {

    }
}
