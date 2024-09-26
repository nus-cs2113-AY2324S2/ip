import tasks.Deadline;
import tasks.Task;
import tasks.ToDo;
import tasks.Event;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static tasks.Deadline.appendDeadlineDuckDataFile;
import static tasks.Event.appendEventDuckDataFile;
import static tasks.ToDo.appendToDoDuckDataFile;

/**
 * Contains methods that deals with storage of tasks which is stored in duck.txt
 */
public class Storage {

    protected static final Path FILE_PATH = Path.of("./data/duck.txt");

    /**
     * Checks if directory and file exists, creates new one if does not exist
     *
     * @throws IOException If an I/O exception occurs during file handling.
     */
    public static void createNewFile() throws IOException {

        if (!Files.isDirectory(FILE_PATH.getParent())) {
            System.out.println("No directory found, creating new one");
            Files.createDirectories(FILE_PATH.getParent());
        }
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }
    }

    /**
     * Reads data duck.txt file and fills the arraylist with the tasked found
     *
     * @param tasks arraylist of tasks
     * @param index number of tasks in arraylist
     * @return index updated number of tasks
     * @throws IOException If an I/O exception occurs during file handling.
     */
    public static int readFile(ArrayList<Task> tasks, int index) throws IOException {
        List<String> fileContent = Files.readAllLines(FILE_PATH);
        for (String line: fileContent) {
            if (line.startsWith("T")) {
                ToDo loadToDo = new ToDo(line.substring(4));
                tasks.add(loadToDo);
            } else if (line.startsWith("D")) {
                String deadlineName = line.split(" \\| by: ")[0].substring(4);
                String deadlineBy = line.split(": ")[1];
                Deadline loadDeadline = new Deadline(deadlineName, deadlineBy);
                tasks.add(loadDeadline);
            } else if (line.startsWith("E")) {
                String eventName = line.split(" \\| from: ")[0].substring(4);
                String eventBy = line.split("from: ")[1].split("by: ")[1];
                String eventFrom = line.split("from: ")[1].split(" \\| by: ")[0];
                Event loadEvent = new Event(eventName, eventFrom + " ", eventBy);
                tasks.add(loadEvent);
            }
            index++;
        }
        return index;
    }

    /**
     * Updates the data storage file duck.txt with the provided list of tasks.
     *
     * @param tasks The ArrayList containing the tasks to be written to the file.
     * @throws IOException If an I/O exception occurs during file handling.
     */
    public static void updateFile (ArrayList<Task> tasks) throws IOException {
        Files.newBufferedWriter(FILE_PATH, StandardOpenOption.TRUNCATE_EXISTING);
        String lineToAdd = "";
        for (Task task: tasks) {
            if (task instanceof ToDo) {
                lineToAdd += appendToDoDuckDataFile((ToDo) task);
            } else if (task instanceof Deadline) {
                lineToAdd += appendDeadlineDuckDataFile((Deadline) task);
            } else if (task instanceof Event) {
                lineToAdd += appendEventDuckDataFile((Event) task);
            }
        }

        Files.write(FILE_PATH, lineToAdd.getBytes(), StandardOpenOption.APPEND);
    }


}
