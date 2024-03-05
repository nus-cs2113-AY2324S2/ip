import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.loadTaskRepresentation(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file doesn't exist
            System.err.println("File not found. Created a new file!");
            System.err.println("Enter a command: ");
            File file = new File(filePath);
            try {
                //If the folder does not exist, create it
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile(); // Create the file if it doesn't exist
            } catch (IOException ioException) {
                System.err.println("Creation of a new file in the sands of time has failed: " + ioException.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks from file.");
        }
        return tasks;
    }

    public static void saveTasks(int listOfTasksSize, ArrayList<Task> listOfTasks) {
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                //If the folder does not exist, create it
                file.getParentFile().mkdirs();
            }
            //If the file doesn't exist, create it
            file.createNewFile();
            try (FileWriter writer = new FileWriter(file)) {
                for (int i = 0; i < listOfTasksSize; i++) {
                    writer.write(listOfTasks.get(i).saveTaskRepresentation() + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
