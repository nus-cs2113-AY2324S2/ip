import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasksToFile(ArrayList<Task> taskList) {
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Task task : taskList) {
                writer.write(task.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks from file.");
        }
    }

    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList=new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                // Assuming the format is: Type | Status | Description | [Additional Info]
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        taskList.add(new Todo(parts[2],parts[1].equals("1")));
                        break;
                    case "D":
                        taskList.add(new Deadline(parts[2], parts[3], parts[1].equals("1")));
                        break;
                    case "E":
                        taskList.add(new Event(parts[2], parts[3],parts[4], parts[1].equals("1")));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file.");
        }
        return taskList;
    }

    public void createFileIfNotExist() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Create directory if it doesn't exist
            file.createNewFile(); // Create file if it doesn't exist
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
