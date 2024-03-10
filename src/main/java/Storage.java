import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                switch (type) {
                    case "T":
                        tasks.add(new TodoTask(description, isDone));
                        break;
                    case "D":
                        String by = parts[3];
                        tasks.add(new DeadlineTask(description, by, isDone));
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        tasks.add(new EventTask(description, from, to, isDone));
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws DukeException {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Error saving tasks: " + e.getMessage());
        }
    }
}
