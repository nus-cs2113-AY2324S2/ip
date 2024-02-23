import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    protected String filePath;
    protected ArrayList<Task> tasks;

    public FileReader (String path) {
        this.filePath = path;
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() throws FileNotFoundException {
        File f = new File(filePath);
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
}
