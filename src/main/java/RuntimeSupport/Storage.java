package RuntimeSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Event.Task;
import Event.Deadline;
import Event.ToDo;
import Event.Event;


public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task = null;

                switch (parts[0]) {
                case "T":
                    task = new ToDo("todo " + parts[2]);
                    break;
                case "D":
                    task = new Deadline("deadline " + parts[2] + " /by " + parts[3]);
                    break;
                case "E":
                    task = new Event("event " + parts[2] + " /from " + parts[3].split(" to ")[0] + " /to " + parts[3].split(" to ")[1]);
                    break;
                }

                if (task != null) {
                    if ("1".equals(parts[1])) {
                        task.markAsDone();
                    }
                    loadedTasks.add(task);
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException();
        }

        return loadedTasks;
    }

    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));

            for (Task task : tasks) {
                writer.write(task.toStorageString() + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }
}
