package Helper;

import Exceptions.LoadFileException;
import Exceptions.SaveFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws LoadFileException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTaskFromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new LoadFileException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasksToFile(ArrayList<Task> taskList) throws SaveFileException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskList) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            throw new SaveFileException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
